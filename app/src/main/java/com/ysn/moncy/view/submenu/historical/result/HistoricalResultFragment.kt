package com.ysn.moncy.view.submenu.historical.result


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ysn.moncy.production.R


/**
 * A simple [Fragment] subclass.
 */
class HistoricalResultFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_historical_result, container, false)
    }

}// Required empty public constructor
