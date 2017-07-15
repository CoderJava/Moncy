package com.ysn.moncy.view.main;

import android.arch.lifecycle.LifecycleActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ysn.moncy.R;
import com.ysn.moncy.view.trend.CurrencyNowActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends LifecycleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({
            R.id.relative_layout_item_menu_currency_now_activity_main,
            R.id.relative_layout_item_menu_list_currency_activity_main,
            R.id.relative_layout_item_menu_historical_currency_activity_main,
            R.id.relative_layout_item_menu_convert_currency_activity_main,
            R.id.relative_layout_item_menu_data_offline_activity_main
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_layout_item_menu_currency_now_activity_main:
                // todo intent to currency now
                startActivity(new Intent(this, CurrencyNowActivity.class));
                break;
            case R.id.relative_layout_item_menu_list_currency_activity_main:
                // todo intent to list currency
                break;
            case R.id.relative_layout_item_menu_historical_currency_activity_main:
                // todo intent to historical currency
                break;
            case R.id.relative_layout_item_menu_convert_currency_activity_main:
                // todo: intent to convert currency
                break;
            case R.id.relative_layout_item_menu_data_offline_activity_main:
                // todo: intent to data offline
                break;
        }
    }
}
