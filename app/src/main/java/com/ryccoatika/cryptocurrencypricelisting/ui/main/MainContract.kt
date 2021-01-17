package com.ryccoatika.cryptocurrencypricelisting.ui.main

import com.ryccoatika.cryptocurrencypricelisting.core.domain.model.Cryptocurrency.*

interface MainContract {
    interface MainView {
        fun onRefreshLoading()
        fun onRefreshStopLoading()
        fun onRefreshEmpty()
        fun onRefreshSuccess(results: List<CryptocurrencyData>)
        fun onRefreshError(message: String)

        fun onLoadNextLoading()
        fun onLoadNextStopLoading()
        fun onLoadNextSuccess(results: List<CryptocurrencyData>)
        fun onLoadNextError(message: String)
    }

    interface IMainPresenter {
        var start: Int
        var end: Int
        var inProgress: Boolean
        fun onRefresh()
        fun onLoadNext()
        fun onDestroy()
    }
}