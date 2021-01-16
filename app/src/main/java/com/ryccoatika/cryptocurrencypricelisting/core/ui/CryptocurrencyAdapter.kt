package com.ryccoatika.cryptocurrencypricelisting.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ryccoatika.cryptocurrencypricelisting.R
import com.ryccoatika.cryptocurrencypricelisting.core.domain.model.Cryptocurrency
import com.ryccoatika.cryptocurrencypricelisting.core.domain.model.formattedPriceUsd
import com.ryccoatika.cryptocurrencypricelisting.core.domain.model.imageUrl
import com.ryccoatika.cryptocurrencypricelisting.ui.utils.loadUrl

class CryptocurrencyAdapter : RecyclerView.Adapter<CryptocurrencyAdapter.CryptocurrencyViewHolder>() {

    private val cryptocurrencies = ArrayList<Cryptocurrency>()

    fun addCryptocurrencies(cryptocurrencies: List<Cryptocurrency>) {
        this.cryptocurrencies.addAll(cryptocurrencies)
        notifyDataSetChanged()
    }

    fun clearCryptocurrencies() {
        this.cryptocurrencies.clear()
        notifyDataSetChanged()
    }

    class CryptocurrencyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val image = view.findViewById<ImageView>(R.id.image)
        private val name = view.findViewById<TextView>(R.id.name)
        private val symbol = view.findViewById<TextView>(R.id.symbol)
        private val price = view.findViewById<TextView>(R.id.price)

        fun bind(cryptocurrency: Cryptocurrency) {
            image.loadUrl(cryptocurrency.imageUrl)
            name.text = cryptocurrency.name
            symbol.text = cryptocurrency.symbol
            price.text = cryptocurrency.formattedPriceUsd
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptocurrencyViewHolder =
        CryptocurrencyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cryptocurrency_list_item, parent, false)
        )

    override fun onBindViewHolder(holder: CryptocurrencyViewHolder, position: Int) {
        holder.bind(cryptocurrencies[position])
    }

    override fun getItemCount(): Int = cryptocurrencies.count()
}