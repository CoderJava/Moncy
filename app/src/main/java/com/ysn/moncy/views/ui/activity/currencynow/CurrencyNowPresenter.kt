package com.ysn.moncy.views.ui.activity.currencynow

import com.ysn.moncy.api.country.CountryEndpoints
import com.ysn.moncy.api.currency.CurrencyEndpoints
import com.ysn.moncy.views.base.mvp.BasePresenter

class CurrencyNowPresenter constructor(private val currencyEndpoints: CurrencyEndpoints,
                                       private val countryEndpoints: CountryEndpoints) : BasePresenter<CurrencyNowView>() {
}