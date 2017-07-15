package com.ysn.moncy.db.dao.trend;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Query;

import com.ysn.moncy.db.entity.trend.CurrencyNowEntity;

import java.util.List;

/**
 * Created by root on 15/07/17.
 */

@Dao
public interface CurrencyNowDao {

    @Query("SELECT * FROM currencynow")
    LiveData<List<CurrencyNowEntity>> loadAllCurrencyNow();

}
