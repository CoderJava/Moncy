
package com.ysn.moncy.model.trend;

public interface CurrencyNow {

    String getPrivacy();
    Quotes getQuotes();
    String getSource();
    boolean getSuccess();
    String getTerms();
    long getTimestamp();

}
