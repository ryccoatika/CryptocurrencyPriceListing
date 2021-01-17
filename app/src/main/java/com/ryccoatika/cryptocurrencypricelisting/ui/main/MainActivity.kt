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
import com.ryccoatika.cryptocurrencypricelisting.ui.utils.showSnackBar
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

        swipeRefresh?.setOnRefreshListener {
            mainPresenter.onRefresh()
            swipeRefresh?.isRefreshing = false // hide swipe refreshing indicator immediately after doing swipe
        }

        // setting up recyclerview
        cryptocurrencyRv?.adapter = adapter
        // add scroll listener
        cryptocurrencyRv?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalItemCount = recyclerView.layoutManager?.itemCount ?: return
                val lastVisibleItemPos = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                if (mainPresenter.inProgress || mainPresenter.start >= mainPresenter.end) return

                if (lastVisibleItemPos + 1 == totalItemCount)
                    mainPresenter.onLoadNext() // loadnext will called when recyclerview scrolled into bottom
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

    override fun onRefreshLoading() {
        adapter.clearCryptocurrencies()
        adapter.showLoading()
    }

    override fun onRefreshStopLoading() {
        adapter.hideLoading()
    }

    override fun onRefreshSuccess(results: List<CryptocurrencyData>) {
        adapter.addCryptocurrencies(results)
    }
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