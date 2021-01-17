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
        end = 0
        start = 0
        inProgress = true
        launch {
            cryptocurrencyInteractor.getAllCoins(start, limit).collect { cryptocurrencies ->
                when(cryptocurrencies) {
                    is Resource.Success -> {
                        end = cryptocurrencies.data.info.coinsNum
                        mainView.onRefreshSuccess(cryptocurrencies.data.data)
                        start += limit
                    }
                    is Resource.Error -> mainView.onRefreshError(cryptocurrencies.message)
                    is Resource.Empty -> mainView.onRefreshEmpty()
                    is Resource.InProgress -> mainView.onRefreshLoading()
                }
            }
            mainView.onRefreshStopLoading()
            inProgress = false
        }
    }

    override fun onLoadNext() {
        inProgress = true
        launch {
            cryptocurrencyInteractor.getAllCoins(start, limit).collect { cryptocurrencies ->
                when {
                    cryptocurrencies is Resource.Success -> {
                        mainView.onLoadNextSuccess(cryptocurrencies.data.data)
                        start += limit
                    }
                    cryptocurrencies is Resource.Error -> mainView.onLoadNextError(cryptocurrencies.message)
                    cryptocurrencies is Resource.InProgress -> mainView.onLoadNextLoading()
                }
            }
            mainView.onLoadNextStopLoading()
            inProgress = false
        }
    }

    override fun onDestroy() {
        job.cancel()
    }
}