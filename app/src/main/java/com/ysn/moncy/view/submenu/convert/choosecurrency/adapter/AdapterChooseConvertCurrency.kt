package com.ysn.moncy.view.submenu.convert.choosecurrency.adapter

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ysn.moncy.R
import com.ysn.moncy.library.svgloader.SvgLoader
import com.ysn.moncy.model.merge.convert.MergeConvertCurrency
import kotlinx.android.synthetic.main.item_data_choose_convert_currency.view.*

/**
 * Created by root on 31/08/17.
 */
class AdapterChooseConvertCurrency(val context: Context, val listMergeConvertCurrency: List<MergeConvertCurrency>) :
        RecyclerView.Adapter<AdapterChooseConvertCurrency.ViewHolderChooseConvertCurrency>() {

    private val TAG = javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolderChooseConvertCurrency {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_data_choose_convert_currency, null)
        return ViewHolderChooseConvertCurrency(view)
    }

    override fun onBindViewHolder(holder: ViewHolderChooseConvertCurrency?, position: Int) {
        val mergeConvertCurrency = listMergeConvertCurrency[position]
        SvgLoader(context).load()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .load(Uri.parse(mergeConvertCurrency.flag))
                .into(holder?.itemView?.image_view_country_flag_item_data_choose_convert_currency)
        holder?.itemView?.text_view_currency_code_item_data_choose_convert_currency?.text = mergeConvertCurrency.currencyCode
    }

    override fun getItemCount(): Int = listMergeConvertCurrency.size

    inner class ViewHolderChooseConvertCurrency(itemView: View?) : RecyclerView.ViewHolder(itemView)

    interface ListenerViewHolderChooseConvertCurrency {

        fun onClick(mergeConvertCurrency: MergeConvertCurrency)

    }

}