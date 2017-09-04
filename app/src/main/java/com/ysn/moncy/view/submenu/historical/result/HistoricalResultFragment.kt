package com.ysn.moncy.view.submenu.historical.result


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ysn.moncy.R
import com.ysn.moncy.model.merge.historical.MergeHistorical
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

    /**
     * On create view fragment
     * @param inflater
     * Layout inflater
     * @param container
     * View group
     * @param savedInstanceState
     * Bundle savedInstanceState
     */
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        initPresenter()
        onAttachView()
        return inflater!!.inflate(R.layout.fragment_historical_result, container, false)
    }

    /**
     * On view created
     * @param view
     * View fragment
     * @param savedInstanceState
     * Bundle savedInstanceState
     */
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text_view_value_source_fragment_historical_result.text = arguments.getString("source")
        text_view_timestamp_fragment_historical_result.text = arguments.getString("date")
        EventBus.getDefault().register(this)
    }

    /**
     * Initialize presenter
     */
    private fun initPresenter() {
        historicalResultPresenter = HistoricalResultPresenter()
    }

    /**
     * Event bus subscribe
     * @param listMergeHistorical
     * Data event bus
     */
    @Subscribe(sticky = true)
    public fun onMessageEvent(listMergeHistorical: ArrayList<MergeHistorical>) {
        Log.d(TAG, "listMergeHistorical: " + listMergeHistorical)
        historicalResultPresenter?.onLoadData(context, listMergeHistorical)

    }

    /**
     * On attach view
     */
    override fun onAttachView() {
        historicalResultPresenter?.onAttach(this)
    }

    /**
     * On detach view
     */
    override fun onDetachView() {
        historicalResultPresenter?.onDetach()
    }

    /**
     * Load data history currency success
     * @param adapterHistoricalResult
     * Adapter historical result for recycler view
     */
    override fun loadData(adapterHistoricalResult: AdapterHistoricalResult) {
        recycler_view_data_fragment_historical_result.layoutManager = LinearLayoutManager(activity!!)
        recycler_view_data_fragment_historical_result.adapter = adapterHistoricalResult
    }
}
