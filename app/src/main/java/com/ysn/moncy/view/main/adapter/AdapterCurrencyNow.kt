package com.ysn.moncy.view.main.adapter

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.ysn.moncy.R
import com.ysn.moncy.model.country.Country
import com.ysn.moncy.model.live.CurrencyNow
import kotlinx.android.synthetic.main.item_currency_now_body.view.*

/**
 * Created by root on 21/07/17.
 */
class AdapterCurrencyNow(context: Context, listCurrencyNow: List<CurrencyNow>, listCountry: List<Country>) : RecyclerView.Adapter<AdapterCurrencyNow.ViewHolder>() {

    private val TAG = javaClass.simpleName
    private var context: Context = context
    private var listCurrencyNow: List<CurrencyNow> = listCurrencyNow
    private var listCountry: List<Country> = listCountry

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
            listCurrencyNow.size - 1 -> {
                /** footer */
            }
            else -> {
                /** body */
                val viewHolderCurrencyNowBody = holder as ViewHolderCurrencyNowBody
                Glide.with(context)
                        .asBitmap()
                        .load(listCountry[position].flag)
                        .apply(RequestOptions.centerCropTransform())
                        .into(object : BitmapImageViewTarget(viewHolderCurrencyNowBody.itemView.image_view_flag_country_item_currency_now_body) {
                            override fun setResource(resource: Bitmap?) {
                                var bitmapRounded = RoundedBitmapDrawableFactory
                                        .create(context.resources, resource)
                                bitmapRounded.isCircular = true
                                viewHolderCurrencyNowBody.itemView
                                        .image_view_flag_country_item_currency_now_body.setImageDrawable(bitmapRounded)
                                // sampai di sini
                            }
                        })
            }
        }
    }

    override fun getItemCount(): Int {
        return listCurrencyNow.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            /** header */
            return 0
        } else if (position == listCurrencyNow.size - 1) {
            /** footer */
            return 2
        } else {
            /** body */
            return 1
        }
    }

    open class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    class ViewHolderCurrencyNowHeader(itemView: View?) : ViewHolder(itemView)

    class ViewHolderCurrencyNowBody(itemView: View?) : ViewHolder(itemView) /*{

        val imageViewFlagCountryViewHolderCurrencyNowBody: ImageView =
                itemView?.findViewById(R.id.image_view_flag_country_item_currency_now_body) as ImageView
        val textViewLabelCurrencyViewHolderCurrencyNowBody: TextView =
                itemView?.findViewById(R.id.text_view_label_currency_item_currency_now_body) as TextView
        val textViewValueCurrencyViewHolderCurrencyNowBody: TextView =
                itemView?.findViewById(R.id.text_view_value_currency_item_currency_now_body) as TextView
        val textViewCountryNameViewHolderCurrencyNowBody: TextView =
                itemView?.findViewById(R.id.text_view_country_name_item_currency_now_body) as TextView

    }*/

    class ViewHolderCurrencyNowFooter(itemView: View?) : ViewHolder(itemView)

}