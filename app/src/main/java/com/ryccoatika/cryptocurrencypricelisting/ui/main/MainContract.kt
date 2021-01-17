package com.ryccoatika.cryptocurrencypricelisting.ui.main

import com.ryccoatika.cryptocurrencypricelisting.core.domain.model.Cryptocurrency

interface MainContract {
    interface MainView {
        fun onRefresh()
        fun onShowLoading()
        fun onHideLoading()
        fun onSuccess(results: List<Cryptocurrency>)
        fun onEmpty()
        fun onError(message: String)
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