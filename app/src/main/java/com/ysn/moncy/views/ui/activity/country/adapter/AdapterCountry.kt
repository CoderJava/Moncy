package com.ysn.moncy.views.ui.activity.country.adapter

import android.content.Context
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ysn.moncy.R
import com.ysn.moncy.library.svgloader.SvgLoader
import com.ysn.moncy.model.country.Country
import org.jetbrains.anko.find

class AdapterCountry constructor(private val context: Context,
                                 private var countries: MutableList<Country>) : RecyclerView.Adapter<AdapterCountry.ViewHolderCountry>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCountry {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_country, null)
        return ViewHolderCountry(view)
    }

    override fun onBindViewHolder(holder: ViewHolderCountry, position: Int) {
        val country = countries[position]
        country.let {
            SvgLoader(context)
                    .load()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .load(Uri.parse(it.flag))
                    .into(holder.imageViewFlag)
            holder.textViewName.text = it.name
            holder.textViewSymbolCurrency.text = it.symbolCurrency
            holder.textViewCapitalCity.text = it.capitalCity
        }
    }

    override fun getItemCount(): Int = countries.size

    fun refresh(countries: MutableList<Country>) {
        this.countries = countries
        notifyDataSetChanged()
    }

    inner class ViewHolderCountry constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val relativeLayoutRoot = itemView.find<RelativeLayout>(R.id.relative_layout_root_container_item_country)
        val imageViewFlag = itemView.find<ImageView>(R.id.image_view_flag_item_country)
        val textViewName = itemView.find<TextView>(R.id.text_view_name_item_country)
        val textViewSymbolCurrency = itemView.find<TextView>(R.id.text_view_symbol_currency_item_country)
        val textViewCapitalCity = itemView.find<TextView>(R.id.text_view_capital_city_item_country)

        init {
            relativeLayoutRoot.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            when (view.id) {
                R.id.relative_layout_root_item_currency_now -> {
                    // TODO: do something in here
                }
                else -> {
                    /* nothing to do in here */
                }
            }
        }

    }

    interface ListenerAdapterCountry {

        fun onClick()

    }

}