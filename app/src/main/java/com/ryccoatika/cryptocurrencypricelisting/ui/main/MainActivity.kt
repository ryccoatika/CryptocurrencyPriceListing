package com.ryccoatika.cryptocurrencypricelisting.ui.main

import android.os.Bundle
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ryccoatika.cryptocurrencypricelisting.R
import com.ryccoatika.cryptocurrencypricelisting.core.domain.model.Cryptocurrency
import com.ryccoatika.cryptocurrencypricelisting.core.domain.usecase.CryptocurrencyUseCase
import com.ryccoatika.cryptocurrencypricelisting.core.ui.CryptocurrencyAdapter
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainContract.MainView {

    private val cryptocurrencyInteractor: CryptocurrencyUseCase by inject()
    private lateinit var mainPresenter: MainContract.IMainPresenter
    private val adapter: CryptocurrencyAdapter by lazy { CryptocurrencyAdapter() }

    // ui
    private var relativeLayout: RelativeLayout? = null
    private var swipeRefresh: SwipeRefreshLayout? = null
    private var cryptocurrencyRv: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter = MainPresenter(this, cryptocurrencyInteractor)

        relativeLayout = findViewById(R.id.relative_layout)
        swipeRefresh = findViewById(R.id.swipe_refresh)
        cryptocurrencyRv = findViewById(R.id.cryptocurrency_rv)

        swipeRefresh?.setOnRefreshListener { mainPresenter.onRefresh() }

        // setting up recyclerview
        cryptocurrencyRv?.adapter = adapter
        cryptocurrencyRv?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = recyclerView.layoutManager?.childCount ?: 0
                val totalItemCount = recyclerView.layoutManager?.itemCount ?: 0
                val firstVisibleItemPos = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if (swipeRefresh?.isRefreshing == true) return

                if (firstVisibleItemPos + visibleItemCount >= totalItemCount)
                    mainPresenter.onLoadNext()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        mainPresenter.onRefresh()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.onDestroy()
    }

    override fun onRefresh() {
        adapter.clearCryptocurrencies()
    }

    override fun onShowLoading() {
        swipeRefresh?.isRefreshing = true
    }

    override fun onHideLoading() {
        swipeRefresh?.isRefreshing = false
        relativeLayout?.showSnackBar(getString(R.string.load_data_error))
    }

    override fun onSuccess(results: List<Cryptocurrency>) {
        adapter.addCryptocurrencies(results)
        Log.d("190401", "onSuccess\ncount: ${results.count()}")
    }

    override fun onEmpty() {
        Log.d("190401", "onEmpty")
    }

    override fun onError(message: String) {
        relativeLayout?.showSnackBar(getString(R.string.load_data_error))
    }
}