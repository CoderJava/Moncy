package com.ysn.moncy.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.ysn.moncy.db.DatabaseCreator;
import com.ysn.moncy.db.entity.trend.CurrencyNowEntity;

import java.util.List;

/**
 * Created by root on 15/07/17.
 */

public class CurrencyNowViewModel extends AndroidViewModel {

    private static final MutableLiveData ABSENT = new MutableLiveData();

    {
        // no inspection unchecked
        ABSENT.setValue(null);
    }

    private final LiveData<List<CurrencyNowEntity>> currencyNowEntityLiveData;
    public ObservableField<List<CurrencyNowEntity>> currencyNowEntityObservableField = new ObservableField<>();

    public CurrencyNowViewModel(@NonNull Application application) {
        super(application);
        final DatabaseCreator databaseCreator = DatabaseCreator.getInstance(this.getApplication());

        currencyNowEntityLiveData = Transformations.switchMap(
                databaseCreator.isDatabaseCreated(),
                new Function<Boolean, LiveData<List<CurrencyNowEntity>>>() {
                    @Override
                    public LiveData<List<CurrencyNowEntity>> apply(Boolean isDbCreated) {
                        if (!isDbCreated) {
                            // No inspection unchecked
                            return ABSENT;
                        } else {
                            // No inspection ConstantConditions
                            return databaseCreator.getAppDatabase()
                                    .currencyNowDao()
                                    .loadAllCurrencyNow();
                        }
                    }
                }
        );

        databaseCreator.createDatabase(this.getApplication());
    }

    /**
     * Expose the LiveData CurrencyNowEntity query so the UI can observe it.
     */
    public ObservableField<List<CurrencyNowEntity>> getCurrencyNowEntityObservableField() {
        return currencyNowEntityObservableField;
    }

    public void setCurrencyNowEntityObservableField(List<CurrencyNowEntity> currencyNowEntityList) {
        this.currencyNowEntityObservableField.set(currencyNowEntityList);
    }

    /**
     * A creator is used to inject the product variable into the ViewModel
     * This creator is to showcase how to inject dependencies into ViewModels.
     * It's not actually necessary in this case, as the variable can be passed in a public method.
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;
        private final int exampleVariable;

        public Factory(@NonNull Application application, int exampleVariable) {
            this.application = application;
            this.exampleVariable = exampleVariable;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            // used it if you want passed a variable
            /*return (T) new CurrencyNowViewModel(application, exampleVariable);*/
            return (T) new CurrencyNowViewModel(application);
        }
    }
}
