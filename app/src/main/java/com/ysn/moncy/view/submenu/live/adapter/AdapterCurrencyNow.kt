package com.ysn.moncy.view.submenu.live.adapter

import android.content.Context
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.ysn.moncy.R
import com.ysn.moncy.library.svgloader.SvgLoader
import com.ysn.moncy.model.merge.live.MergeLive
import kotlinx.android.synthetic.main.item_currency_now_body.view.*

/**
 * Created by root on 21/07/17.
 */
class AdapterCurrencyNow(private var context: Context, private var listMergeLive: ArrayList<MergeLive>)
    : RecyclerView.Adapter<AdapterCurrencyNow.ViewHolder>() {

    private val TAG = javaClass.simpleName

    /**
     * On create view holder
     * @param parent
     * View group parent
     * @param viewType
     * View type view holder
     */
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view: View
        val inflater = LayoutInflater.from(parent?.context)
        when (viewType) {
            0 -> {
                /** header */
                view = inflater.inflate(R.layout.item_currency_now_header, null)
                return ViewHolderCurrencyNowHeader(view)
            }
            2 -> {
                /** footer */
                view = inflater.inflate(R.layout.item_currency_now_footer, null)
                return ViewHolderCurrencyNowFooter(view)
            }
            else -> {
                /** body */
                view = inflater.inflate(R.layout.item_currency_now_body, null)
                return ViewHolderCurrencyNowBody(view)
            }
        }
    }

    /**
     * On bind view holder
     * @param holder
     * view holder for adapter
     * @param position
     * Position view holder
     */
    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        when (position) {
            0 -> {
                /** header */
            }
            listMergeLive.size - 1 -> {
                /** footer */
            }
            else -> {
                /** body */
                val viewHolderCurrencyNowBody = holder as ViewHolderCurrencyNowBody

                /** load image */
                SvgLoader(context).load()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .load(Uri.parse(listMergeLive[position].flag))
                        .into(viewHolderCurrencyNowBody.itemView.image_view_country_flag_item_currency_now_body)
                viewHolderCurrencyNowBody.itemView
                        .text_view_label_currency_item_currency_now_body
                        .text = listMergeLive[position].label
                viewHolderCurrencyNowBody.itemView
                        .text_view_value_currency_item_currency_now_body
                        .text = listMergeLive[position].value
                viewHolderCurrencyNowBody.itemView
                        .text_view_country_name_item_currency_now_body
                        .text = listMergeLive[position].countryName
            }
        }
    }

    /**
     * Get item count adapter
     */
    override fun getItemCount(): Int = listMergeLive.size

    /**
     * Get item view type view holder for adapter
     * @param position
     * Position view holder for adapter
     */
    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            /** header */
            0
        } else if (position == listMergeLive.size - 1) {
            /** footer */
            2
        } else {
            /** body */
            1
        }
    }

    /**
     * View holder parent extends RecyclerView.ViewHolder
     * @param itemView
     * Item view holder
     */
    inner open class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    /**
     * View holder header extends View holder parent
     * @param itemView
     * Item view holder header
     */
    inner class ViewHolderCurrencyNowHeader(itemView: View?) : ViewHolder(itemView)

    /**
     * View holder body extends View holder parent
     * @param itemView
     * Item view holder body
     */
    inner class ViewHolderCurrencyNowBody(itemView: View?) : ViewHolder(itemView)

    /**
     * View holder footer extends view holder parent
     * @param itemView
     * Item view holder footer
     */
    inner class ViewHolderCurrencyNowFooter(itemView: View?) : ViewHolder(itemView)

}

