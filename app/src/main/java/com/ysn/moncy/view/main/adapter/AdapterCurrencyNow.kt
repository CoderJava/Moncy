package com.ysn.moncy.view.main.adapter

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.ysn.moncy.R
import com.ysn.moncy.model.country.Country
import com.ysn.moncy.model.merge.MergeLive
import kotlinx.android.synthetic.main.item_currency_now_body.view.*

/**
 * Created by root on 21/07/17.
 */
class AdapterCurrencyNow(context: Context, listMergeLive: ArrayList<MergeLive>) : RecyclerView.Adapter<AdapterCurrencyNow.ViewHolder>() {

    private val TAG = javaClass.simpleName
    private var context: Context = context
    private var listMergeLive = listMergeLive

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
            listMergeLive.size - 1 -> {
                /** footer */
            }
            else -> {
                /** body */
                val viewHolderCurrencyNowBody = holder as ViewHolderCurrencyNowBody

                /** failed load image */
                /*Glide.with(context)
                        .asBitmap()
                        .load(listMergeLive[position].flag)
                        .apply(RequestOptions.centerCropTransform())
                        .into(object : BitmapImageViewTarget(viewHolderCurrencyNowBody.itemView.image_view_flag_country_item_currency_now_body) {
                            override fun setResource(resource: Bitmap?) {
                                val bitmapRounded = RoundedBitmapDrawableFactory
                                        .create(context.resources, resource)
                                bitmapRounded.isCircular = true
                                viewHolderCurrencyNowBody.itemView
                                        .image_view_flag_country_item_currency_now_body
                                        .setImageDrawable(bitmapRounded)
                            }
                        })*/
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

    override fun getItemCount(): Int {
        return listMergeLive.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            /** header */
            return 0
        } else if (position == listMergeLive.size - 1) {
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

