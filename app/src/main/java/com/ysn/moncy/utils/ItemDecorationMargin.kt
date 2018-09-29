package com.ysn.moncy.utils

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.ysn.moncy.R

class ItemDecorationMargin constructor(private val context: Context) : RecyclerView.ItemDecoration() {

    private val decorationHeight: Int = context.resources.getDimensionPixelSize(R.dimen.margin_16dp)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val itemPosition = parent.getChildAdapterPosition(view)
        val totalCount = parent.adapter!!.itemCount
        if (itemPosition >= 0 && itemPosition < totalCount - 1) {
            outRect.bottom = decorationHeight
        }
    }

}