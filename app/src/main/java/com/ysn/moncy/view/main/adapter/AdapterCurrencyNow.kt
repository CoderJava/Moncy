package com.ysn.moncy.view.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ysn.moncy.R
import kotlinx.android.synthetic.main.item_currency_now_body.view.*

/**
 * Created by root on 21/07/17.
 */
class AdapterCurrencyNow(context: Context, listQuotesLabels: ArrayList<String>, listQuotesValues: ArrayList<String>) : RecyclerView.Adapter<AdapterCurrencyNow.ViewHolder>() {

    private val TAG = javaClass.simpleName
    private var context: Context = context
    private var listQuotesLabels = listQuotesLabels
    private var listQuotesValues = listQuotesValues

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

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        when (position) {
            0 -> {
                /** header */
            }
            listQuotesLabels.size - 1 -> {
                /** footer */
            }
            else -> {
                /** body */
                val viewHolderCurrencyNowBody = holder as ViewHolderCurrencyNowBody
                /*Glide.with(context)
                        .asBitmap()
                        .load(listCountry[position])
                        .apply(RequestOptions.centerCropTransform())
                        .into(object : BitmapImageViewTarget(viewHolderCurrencyNowBody.itemView.image_view_flag_country_item_currency_now_body) {
                            override fun setResource(resource: Bitmap?) {
                                var bitmapRounded = RoundedBitmapDrawableFactory
                                        .create(context.resources, resource)
                                bitmapRounded.isCircular = true
                                viewHolderCurrencyNowBody.itemView
                                        .image_view_flag_country_item_currency_now_body
                                        .setImageDrawable(bitmapRounded)
                            }
                        })*/
                viewHolderCurrencyNowBody.itemView
                        .text_view_label_currency_item_currency_now_body
                        .text = listQuotesLabels[position]
                viewHolderCurrencyNowBody.itemView
                        .text_view_value_currency_item_currency_now_body
                        .text = listQuotesValues[position]
            }
        }
    }

    override fun getItemCount(): Int {
        return listQuotesLabels.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            /** header */
            return 0
        } else if (position == listQuotesLabels.size - 1) {
            /** footer */
            return 2
        } else {
            /** body */
            return 1
        }
    }

    inner open class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    inner class ViewHolderCurrencyNowHeader(itemView: View?) : ViewHolder(itemView)

    inner class ViewHolderCurrencyNowBody(itemView: View?) : ViewHolder(itemView)

    inner class ViewHolderCurrencyNowFooter(itemView: View?) : ViewHolder(itemView)

}

