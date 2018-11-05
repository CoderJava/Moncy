package com.ysn.moncy.views.ui.activity.currencynow.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import com.ysn.moncy.R
import com.ysn.moncy.model.currencynow.Rate
import com.ysn.moncy.model.currencynow.Result
import com.ysn.moncy.utils.gone
import com.ysn.moncy.utils.visible
import org.jetbrains.anko.find

class AdapterCurrencyNow(private var rates: MutableList<Rate>,
                         private var reverseRates: MutableList<Rate>,
                         private val listenerAdapterCurrencyNow: ListenerAdapterCurrencyNow) : RecyclerView.Adapter<AdapterCurrencyNow.ViewHolderCurrencyNow>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCurrencyNow {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_currency_now, null)
        return ViewHolderCurrencyNow(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolderCurrencyNow, position: Int) {
        val rate = rates[position]
        val reverseRate = reverseRates[position]
        holder.textViewRateName.text = rate.name.substring(3, rate.name.length)
        holder.textViewRateValue.text = rate.value.toString()

        if (reverseRate.name == "loading") {
            holder.textViewReverseRateName.gone()
            holder.textViewReverseRateValue.gone()
            holder.textViewTapForDetail.gone()
            holder.progressBarLoading.visible()
        } else if (!reverseRate.name.isEmpty()) {
            holder.textViewReverseRateName.text = reverseRate.name.replace("_", "->")
            holder.textViewReverseRateValue.text = reverseRate.value.toString()
            holder.textViewReverseRateName.visible()
            holder.textViewReverseRateValue.visible()
            holder.textViewTapForDetail.gone()
            holder.progressBarLoading.gone()
        } else {
            holder.textViewReverseRateName.gone()
            holder.textViewReverseRateValue.gone()
            holder.textViewTapForDetail.visible()
            holder.progressBarLoading.gone()
        }
    }

    override fun getItemCount(): Int = rates.size

    fun refresh(rates: MutableList<Rate>, reverseRates: MutableList<Rate>) {
        this.rates = rates
        this.reverseRates = reverseRates
        notifyDataSetChanged()
    }

    fun updateItem(key: String, result: Result, adapterPosition: Int) {
        reverseRates[adapterPosition] = Rate(name = key, value = result.valX)
        notifyDataSetChanged()
    }

    inner class ViewHolderCurrencyNow constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val relativeLayoutRoot = itemView.find<RelativeLayout>(R.id.relative_layout_root_item_currency_now)
        val textViewRateName = itemView.find<TextView>(R.id.text_view_rate_name_item_currency_now)
        val textViewRateValue = itemView.find<TextView>(R.id.text_view_rate_value_item_currency_now)
        val textViewTapForDetail = itemView.find<TextView>(R.id.text_view_tap_for_detail_item_currency_now)
        val progressBarLoading = itemView.find<ProgressBar>(R.id.progress_bar_loading_item_currency_now)
        val textViewReverseRateName = itemView.find<TextView>(R.id.text_view_reverse_rate_name_item_currency_now)
        val textViewReverseRateValue = itemView.find<TextView>(R.id.text_view_reverse_rate_value_item_currency_now)

        init {
            relativeLayoutRoot.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            when (view.id) {
                R.id.relative_layout_root_item_currency_now -> {
                    if (reverseRates[adapterPosition].name.isEmpty()) {
                        listenerAdapterCurrencyNow.onClick(adapterPosition = adapterPosition, name = rates[adapterPosition].name)
                    }
                }
                else -> {
                    /* nothing to do in here */
                }
            }
        }

    }

    interface ListenerAdapterCurrencyNow {

        fun onClick(adapterPosition: Int, name: String)

    }

}