package com.deliverylocator;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDex;

import com.deliverylocator.database.DbOpenHelper;
import com.deliverylocator.entity.DaoMaster;
import com.deliverylocator.entity.DaoSession;

public class DeliveryLocatorApplication extends Application {
    private DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        // This flag should be set to true to enable VectorDrawable support for API < 21.
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        try {
            MultiDex.install(this);
        } catch (Exception ex) {
            //nothing to do
            ex.printStackTrace();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mDaoSession = new DaoMaster(new DbOpenHelper(base, "DeliveryLocator").getWritableDb()).newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
