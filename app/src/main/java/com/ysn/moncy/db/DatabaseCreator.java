package com.ysn.moncy.db;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.Nullable;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by root on 15/07/17.
 * Create the {@link AppDatabase} asynchronously, exposing a LiveData object to notify of creation.
 */

public class DatabaseCreator {

    private static DatabaseCreator instance;
    private final MutableLiveData<Boolean> isDatabaseCreated = new MutableLiveData<>();
    private AppDatabase appDatabase;
    private final AtomicBoolean atomicBoolean = new AtomicBoolean(true);

    // For Singleton instantiation
    private static final Object LOCK = new Object();

    public synchronized static DatabaseCreator getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new DatabaseCreator();
                }
            }
        }
        return instance;
    }

    /**
     * Used to observe when the database initialization is done
     */
    public LiveData<Boolean> isDatabaseCreated() {
        return isDatabaseCreated;
    }

    @Nullable
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    /**
     * Creates or returns a previously-created databases.
     * Although this uses an AsyncTask or Rx which currently uses a serial executor, it's thread safe
     */
    public void createDatabase(final Context context) {
        if (!atomicBoolean.compareAndSet(true, false)) {
            return; // Already initializing
        }

        isDatabaseCreated.setValue(false);  // Trigger an update to show a loading screen
        Observable
                .fromCallable(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        // Reset the database to have new data on every run
                        context.deleteDatabase(AppDatabase.DATABASE_NAME);

                        // Build the database
                        appDatabase = Room.databaseBuilder(context,
                                AppDatabase.class, AppDatabase.DATABASE_NAME).build();

                        return true;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<Boolean>() {
                            @Override
                            public void accept(@NonNull Boolean aBoolean) throws Exception {
                                isDatabaseCreated.setValue(true);
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                isDatabaseCreated.setValue(false);
                                throwable.printStackTrace();
                            }
                        }
                );
    }
}
