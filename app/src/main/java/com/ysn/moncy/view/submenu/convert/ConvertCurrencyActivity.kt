package com.ysn.moncy.view.submenu.convert

import android.app.ProgressDialog
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.load.engine.DiskCacheStrategy

import com.ysn.moncy.R
import com.ysn.moncy.library.svgloader.SvgLoader
import com.ysn.moncy.model.merge.convert.MergeConvertCurrency
import com.ysn.moncy.view.submenu.convert.choosecurrency.ChooseConvertCurrencyBottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_convert_currency.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class ConvertCurrencyActivity : AppCompatActivity(), ConvertCurrencyView, View.OnClickListener {

    private val TAG = javaClass.simpleName
    private var convertCurrencyPresenter: ConvertCurrencyPresenter? = null
    private lateinit var progressDialog: ProgressDialog
    private lateinit var listMergeConvertCurrency: List<MergeConvertCurrency>
    private var valueCurrencyTo: Double? = 0.0

    /**
     * On create
     * @param savedInstanceState
     * Bundle savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convert_currency)
        initPresenter()
        onAttachView()
        EventBus.getDefault().register(this)
        initToolbar()
        initListener()
        doLoadData()
    }

    /**
     * Initialize toolbar
     */
    private fun initToolbar() {
        toolbar_activity_convert_currency.title = "Convert Currency"
        setSupportActionBar(toolbar_activity_convert_currency)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    /**
     * Initialize listener for UI view
     */
    private fun initListener() {
        text_view_value_source_code_currency_activity_convert_currency.setOnClickListener(this)
        image_view_icon_drop_down_value_source_currency_activity_convert_currency.setOnClickListener(this)
        text_view_value_to_code_currency_activity_convert_currency.setOnClickListener(this)
        image_view_icon_drop_down_value_to_currency_activity_convert_currency.setOnClickListener(this)
        button_try_again_activity_convert_currency.setOnClickListener(this)
        button_keypad_0_activity_convert_currency.setOnClickListener(this)
        button_keypad_1_activity_convert_currency.setOnClickListener(this)
        button_keypad_2_activity_convert_currency.setOnClickListener(this)
        button_keypad_3_activity_convert_currency.setOnClickListener(this)
        button_keypad_4_activity_convert_currency.setOnClickListener(this)
        button_keypad_5_activity_convert_currency.setOnClickListener(this)
        button_keypad_6_activity_convert_currency.setOnClickListener(this)
        button_keypad_7_activity_convert_currency.setOnClickListener(this)
        button_keypad_8_activity_convert_currency.setOnClickListener(this)
        button_keypad_9_activity_convert_currency.setOnClickListener(this)
        button_keypad_dot_activity_convert_currency.setOnClickListener(this)
        button_keypad_backspace_activity_convert_currency.setOnClickListener(this)
        button_keypad_clear_activity_convert_currency.setOnClickListener(this) }

    /**
     * Method implements View.OnClickListener
     * @param view
     * View clicked
     */
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.text_view_value_source_code_currency_activity_convert_currency,
            R.id.image_view_icon_drop_down_value_source_currency_activity_convert_currency -> {
                val chooseConvertCurrencyBottomSheetDialogFragment = ChooseConvertCurrencyBottomSheetDialogFragment()
                chooseConvertCurrencyBottomSheetDialogFragment.show(
                        supportFragmentManager,
                        "valueSource"
                )
                EventBus.getDefault().postSticky(listMergeConvertCurrency)
            }
            R.id.text_view_value_to_code_currency_activity_convert_currency,
            R.id.image_view_icon_drop_down_value_to_currency_activity_convert_currency -> {
                val chooseConvertCurrencyBottomSheetDialogFragment = ChooseConvertCurrencyBottomSheetDialogFragment()
                chooseConvertCurrencyBottomSheetDialogFragment.show(
                        supportFragmentManager,
                        "toSource"
                )
                EventBus.getDefault().postSticky(listMergeConvertCurrency)
            }
            R.id.button_try_again_activity_convert_currency -> {
                doLoadData()
            }
            R.id.button_keypad_0_activity_convert_currency -> {
                updateSourceAmount(keypad = "0")
            }
            R.id.button_keypad_1_activity_convert_currency -> {
                updateSourceAmount(keypad = "1")
            }
            R.id.button_keypad_2_activity_convert_currency -> {
                updateSourceAmount(keypad = "2")
            }
            R.id.button_keypad_3_activity_convert_currency -> {
                updateSourceAmount(keypad = "3")
            }
            R.id.button_keypad_4_activity_convert_currency -> {
                updateSourceAmount(keypad = "4")
            }
            R.id.button_keypad_5_activity_convert_currency -> {
                updateSourceAmount(keypad = "5")
            }
            R.id.button_keypad_6_activity_convert_currency -> {
                updateSourceAmount(keypad = "6")
            }
            R.id.button_keypad_7_activity_convert_currency -> {
                updateSourceAmount(keypad = "7")
            }
            R.id.button_keypad_8_activity_convert_currency -> {
                updateSourceAmount(keypad = "8")
            }
            R.id.button_keypad_9_activity_convert_currency -> {
                updateSourceAmount(keypad = "9")
            }
            R.id.button_keypad_dot_activity_convert_currency -> {
                updateSourceAmount(keypad = ".")
            }
            R.id.button_keypad_backspace_activity_convert_currency -> {
                updateSourceAmount(keypad = "x")
            }
            R.id.button_keypad_clear_activity_convert_currency -> {
                updateSourceAmount(keypad = "clear")
            }
            else -> {
                /** nothing to do in here */
            }
        }
    }

    /**
     * Update view source amount
     * @param keypad
     * Keypad clicked
     */
    private fun updateSourceAmount(keypad: String) {
        val sourceAmount = text_view_source_amount_activity_convert_currency.text.let {
            when {
                it == "0" && keypad != "x" && keypad != "clear" -> {
                    keypad
                }
                keypad == "x" -> {
                    if (it.length > 1) {
                        it.substring(0, it.length - 1)
                    } else {
                        "0"
                    }
                }
                keypad == "clear" -> {
                    "0"
                }
                else -> {
                    "$it$keypad"
                }
            }
        }
        text_view_source_amount_activity_convert_currency.text = sourceAmount
        if (text_view_value_source_code_currency_activity_convert_currency.text == text_view_value_to_code_currency_activity_convert_currency.text) {
            text_view_to_amount_activity_convert_currency.text = text_view_source_amount_activity_convert_currency.text
        } else {
            Log.d(TAG, "valueConverterCurrency: $valueCurrencyTo!!")
            text_view_to_amount_activity_convert_currency.text = convertCurrencyPresenter?.doConvertCurrency(
                    sourceAmount = sourceAmount.toDouble(),
                    valueConverterCurrency = valueCurrencyTo!!
            ).toString()
        }
    }

    /**
     * Do load data currency to get currency code and flag country
     */
    private fun doLoadData() {
        initProgressDialog()
        linear_layout_container_content_activity_convert_currency.visibility = View.GONE
        relative_layout_container_refresh_activity_convert_currency.visibility = View.GONE
        convertCurrencyPresenter?.onLoadData(this)
    }

    /**
     * Initialize progress dialog
     */
    private fun initProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage(getString(R.string.please_wait))
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

    /**
     * On resume activity
     */
    override fun onResume() {
        onAttachView()
        super.onResume()
    }

    /**
     * On destroy activity
     */
    override fun onDestroy() {
        super.onDestroy()
        onDetachView()
    }

    /**
     * Initialize presenter
     */
    private fun initPresenter() {
        convertCurrencyPresenter = ConvertCurrencyPresenter()
    }

    /**
     * On attach view
     */
    override fun onAttachView() {
        convertCurrencyPresenter?.onAttach(this)
    }

    /**
     * On detach view
     */
    override fun onDetachView() {
        convertCurrencyPresenter?.onDetach()
    }

    /**
     * On options item selected
     * @param item
     * Menu item selected
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    /**
     * Load data success to get currency code and flag country
     * @param listMergeConvertCurrency
     * List data merge convert currency
     */
    override fun loadData(listMergeConvertCurrency: List<MergeConvertCurrency>) {
        this.listMergeConvertCurrency = listMergeConvertCurrency
        Log.d(TAG, "listMergeConvertCurrency: $listMergeConvertCurrency")
        setProgressViewDone()
        linear_layout_container_content_activity_convert_currency.visibility = View.VISIBLE
        relative_layout_container_refresh_activity_convert_currency.visibility = View.GONE
        doLoadDataConverterCurrency()
    }

    /**
     * Load data currency failed to get currency code and flag country
     */
    override fun loadDataFailed() {
        showSnackbarFailed()
        setProgressViewDone()
        linear_layout_container_content_activity_convert_currency.visibility = View.GONE
        relative_layout_container_refresh_activity_convert_currency.visibility = View.VISIBLE
        doLoadDataConverterCurrency()
    }

    /**
     * Show message failed in snack bar
     * @param message
     * Message failed
     */
    private fun showSnackbarFailed(message: String = getString(R.string.load_data_failed)) {
        Snackbar.make(
                findViewById(android.R.id.content),
                message,
                Snackbar.LENGTH_LONG
        ).show()
    }

    /**
     * Set progress view is done or dismiss
     */
    private fun setProgressViewDone() {
        progressDialog.dismiss()
    }

    /**
     * Event bus subscribe
     * @param mapData
     * Data subscribe from event bus ChooseConvertCurrencyBottomSheetDialogFragment
     */
    @Subscribe
    fun onMessageEvent(mapData: Map<String, Any>) {
        val fromView = mapData.get("fromView") as String
        val mergeConvertCurrency = mapData.get("data") as MergeConvertCurrency
        valueCurrencyTo = 0.0
        if (fromView == "valueSource") {
            text_view_value_source_code_currency_activity_convert_currency.text = mergeConvertCurrency
                    .currencyCode
            SvgLoader(this).load()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .load(Uri.parse(mergeConvertCurrency.flag))
                    .into(image_view_source_country_activity_convert_currency)
            doLoadDataConverterCurrency()
        } else {
            text_view_value_to_code_currency_activity_convert_currency.text = mergeConvertCurrency
                    .currencyCode
            SvgLoader(this).load()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .load(Uri.parse(mergeConvertCurrency.flag))
                    .into(image_view_to_country_activity_convert_currency)
            doLoadDataConverterCurrency()
        }
    }

    /**
     * Do load data converter currency
     */
    private fun doLoadDataConverterCurrency() {
        progress_bar_loading_activty_convert_currency.visibility = View.VISIBLE
        text_view_to_amount_activity_convert_currency.visibility = View.INVISIBLE
        convertCurrencyPresenter?.onLoadDataConverterCurrency(
                sourceCode = text_view_value_source_code_currency_activity_convert_currency
                        .text
                        .toString(),
                toCode = text_view_value_to_code_currency_activity_convert_currency
                        .text
                        .toString()
        )
    }

    /**
     * Load data converter currency success
     * @param valueConverterCurrency
     * Result converter currency
     */
    override fun loadDataConverterCurrency(valueConverterCurrency: Double) {
        this.valueCurrencyTo = valueConverterCurrency
        progress_bar_loading_activty_convert_currency.visibility = View.GONE
        text_view_to_amount_activity_convert_currency.visibility = View.VISIBLE
        val sourceAmount = text_view_source_amount_activity_convert_currency.text
                .toString()
                .toDouble()
        val toAmount = convertCurrencyPresenter?.doConvertCurrency(
                sourceAmount = sourceAmount,
                valueConverterCurrency = valueConverterCurrency
        )
        text_view_to_amount_activity_convert_currency.text = toAmount.toString()
    }

    /**
     * Load data converter currency failed
     */
    override fun loadDataConverterCurrencyFailed() {
        progress_bar_loading_activty_convert_currency.visibility = View.GONE
        text_view_to_amount_activity_convert_currency.visibility = View.VISIBLE
        text_view_to_amount_activity_convert_currency.text = "0"
        showSnackbarFailed(getString(R.string.convert_currency_failed))
    }
}
