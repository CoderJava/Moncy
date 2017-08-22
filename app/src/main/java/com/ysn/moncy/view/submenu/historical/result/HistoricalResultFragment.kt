package com.ysn.moncy.view.submenu.historical.result


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ysn.moncy.R
import com.ysn.moncy.model.merge.live.MergeHistorical
import com.ysn.moncy.view.submenu.historical.result.adapter.AdapterHistoricalResult
import kotlinx.android.synthetic.main.fragment_historical_result.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


/**
 * A simple [Fragment] subclass.
 */
class HistoricalResultFragment : Fragment(), HistoricalResultView {

    private val TAG = javaClass.simpleName
    private var historicalResultPresenter: HistoricalResultPresenter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        initPresenter()
        onAttachView()
        return inflater!!.inflate(R.layout.fragment_historical_result, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EventBus.getDefault().register(this)
    }

    private fun initPresenter() {
        historicalResultPresenter = HistoricalResultPresenter()
    }

    @Subscribe(sticky = true)
    public fun onMessageEvent(listMergeHistorical: ArrayList<MergeHistorical>) {
        Log.d(TAG, "listMergeHistorical: " + listMergeHistorical)
        historicalResultPresenter?.onLoadData(context, listMergeHistorical)

    }

    override fun onAttachView() {
        historicalResultPresenter?.onAttach(this)
    }

    override fun onDetachView() {
        historicalResultPresenter?.onDetach()
    }

    override fun loadData(adapterHistoricalResult: AdapterHistoricalResult) {
        recycler_view_data_fragment_historical_result.layoutManager = LinearLayoutManager(activity)
        recycler_view_data_fragment_historical_result.adapter = adapterHistoricalResult
    }
}
