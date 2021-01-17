package com.ryccoatika.cryptocurrencypricelisting.ui.main

import com.ryccoatika.cryptocurrencypricelisting.core.data.Resource
import com.ryccoatika.cryptocurrencypricelisting.core.domain.usecase.CryptocurrencyUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainPresenter(
    private val mainView: MainContract.MainView,
    private val cryptocurrencyInteractor: CryptocurrencyUseCase,
    private val uiContext: CoroutineContext = Dispatchers.Main
): MainContract.IMainPresenter, CoroutineScope {

    // for pagination
    override var start = 0
    override var end = 0
    private var limit = 10
    override var inProgress = true

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = uiContext + job

    override fun onRefresh() {
        start = 0
        mainView.onRefresh()
        loadData()
    }

    override fun onLoadNext() {
        start += limit
        loadData()
    }

    private fun loadData() {
        launch {
            cryptocurrencyInteractor.getAllCoins(start, limit).collect { cryptocurrencies ->
                when(cryptocurrencies) {
                    is Resource.Success -> mainView.onSuccess(cryptocurrencies.data.sortedBy { it.rank })
                    is Resource.Error -> {
                        mainView.onError(cryptocurrencies.message)
                        if (start >= 0) start -= limit
                    }
                    is Resource.Empty -> mainView.onEmpty()
                    is Resource.InProgress -> mainView.onShowLoading()
                }
            }
            mainView.onHideLoading()
        }
    }

    override fun onDestroy() {
        job.cancel()
    }
}