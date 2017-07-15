package com.ysn.moncy.db.entity.trend;

import android.arch.persistence.room.Entity;

import com.ysn.moncy.model.trend.CurrencyNow;
import com.ysn.moncy.model.trend.Quotes;

/**
 * Created by root on 15/07/17.
 */

@Entity(tableName = "currencynow")
public class CurrencyNowEntity implements CurrencyNow {

    private String privacy;
    private Quotes quotes;
    private String source;
    private boolean isSuccess;
    private String terms;
    private long timestamp;

    public CurrencyNowEntity(CurrencyNow currencyNow) {
        this.privacy = currencyNow.getPrivacy();
        this.quotes = currencyNow.getQuotes();
        this.source = currencyNow.getSource();
        this.isSuccess = currencyNow.getSuccess();
        this.terms = currencyNow.getTerms();
        this.timestamp = currencyNow.getTimestamp();
    }

    @Override
    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    @Override
    public Quotes getQuotes() {
        return quotes;
    }

    public void setQuotes(Quotes quotes) {
        this.quotes = quotes;
    }

    @Override
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    @Override
    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
