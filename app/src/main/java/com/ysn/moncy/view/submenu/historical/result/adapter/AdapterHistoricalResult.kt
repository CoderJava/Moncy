package com.ysn.moncy.view.submenu.historical.result.adapter

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ysn.moncy.R
import com.ysn.moncy.library.svgloader.SvgLoader
import com.ysn.moncy.model.merge.historical.MergeHistorical
import kotlinx.android.synthetic.main.item_historical_result_body.view.*

/**
 * Created by root on 22/08/17.
 */
class AdapterHistoricalResult(private var context: Context, private var listMergeHistorical: ArrayList<MergeHistorical>)
    : RecyclerView.Adapter<AdapterHistoricalResult.ViewHolder>() {

    private val TAG = javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view: View
        val inflater = LayoutInflater.from(parent?.context)
        when (viewType) {
            0 -> {
                /** header */
                view = inflater.inflate(R.layout.item_historical_result_header, null);
                return ViewHolderHistoricalResultHeader(view)
            }
            2 -> {
                /** footer */
                view = inflater.inflate(R.layout.item_historical_result_footer, null)
                return ViewHolderHistoricalResultFooter(view)
            }
            else -> {
                /** body */
                view = inflater.inflate(R.layout.item_historical_result_body, null)
                return ViewHolderHistoricalResultBody(view)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        when (position) {
            0 -> {
                /** header */
            }
            listMergeHistorical.size - 1 -> {
                /** footer */
            }
            else -> {
                /** body */
                val viewHolderHistoricalResultBody = holder as ViewHolderHistoricalResultBody

                /** load image */
                SvgLoader(context).load()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .load(Uri.parse(listMergeHistorical[position].flag))
                        .into(viewHolderHistoricalResultBody.itemView.image_view_country_flag_item_historical_result_body)
                viewHolderHistoricalResultBody.itemView
                        .text_view_label_currency_item_historical_result_body
                        .text = listMergeHistorical[position].label
                viewHolderHistoricalResultBody.itemView
                        .text_view_value_currency_item_historical_result_body
                        .text = listMergeHistorical[position].value
                viewHolderHistoricalResultBody.itemView
                        .text_view_country_name_item_historical_result_body
                        .text = listMergeHistorical[position].countryName
            }
        }
    }

    override fun getItemCount(): Int = listMergeHistorical.size

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            /** header */
            0
        } else if (position == listMergeHistorical.size - 1) {
            /** footer */
            2
        } else {
            /** body */
            1
        }
    }

    inner open class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    inner class ViewHolderHistoricalResultHeader(itemView: View?) : ViewHolder(itemView)

    inner class ViewHolderHistoricalResultBody(itemView: View?) : ViewHolder(itemView)

    inner class ViewHolderHistoricalResultFooter(itemView: View?) : ViewHolder(itemView)

}