package com.ysn.moncy.view.submenu.available.adapter

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ysn.moncy.R
import com.ysn.moncy.library.svgloader.SvgLoader
import com.ysn.moncy.model.merge.available.MergeAvailable
import kotlinx.android.synthetic.main.item_available_currency.view.*

/**
 * Created by root on 06/08/17.
 */
class AdapterAvailableCurrency(context: Context, listMergeAvailable: ArrayList<MergeAvailable>)
    : RecyclerView.Adapter<AdapterAvailableCurrency.ViewHolderAvailableCurrency>() {

    private val TAG = javaClass.simpleName
    private val context = context
    private val listMergeAvailable = listMergeAvailable

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolderAvailableCurrency {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_available_currency, null)
        return ViewHolderAvailableCurrency(view)
    }

    override fun onBindViewHolder(holder: ViewHolderAvailableCurrency?, position: Int) {
        val mergeAvailable = listMergeAvailable[position]
        SvgLoader(context).load()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .load(Uri.parse(mergeAvailable.flag))
                .into(holder?.itemView?.
                        image_view_country_flag_item_available_currency
                )
        holder?.itemView?.
                text_view_country_name_item_available_currency?.
                text = mergeAvailable.name
        holder?.itemView?.
                text_view_currency_code_item_available_currency?.
                text = mergeAvailable.codeCurrency
        holder?.itemView?.
                text_view_country_region_item_available_currency?.
                text = mergeAvailable.region
    }

    override fun getItemCount(): Int = listMergeAvailable.size

    inner class ViewHolderAvailableCurrency(itemView: View?) : RecyclerView.ViewHolder(itemView)

}