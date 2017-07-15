package com.ysn.moncy.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.ysn.moncy.db.dao.trend.CurrencyNowDao;
import com.ysn.moncy.db.entity.trend.CurrencyNowEntity;

/**
 * Created by root on 15/07/17.
 */

@Database(entities = {CurrencyNowEntity.class}, version = 1)
/*@TypeConverters(DateConverter.class)*/
public abstract class AppDatabase extends RoomDatabase {

    static final String DATABASE_NAME = "moncy.db";

    public abstract CurrencyNowDao currencyNowDao();

}
