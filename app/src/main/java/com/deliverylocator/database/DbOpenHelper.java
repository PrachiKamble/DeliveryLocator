package com.deliverylocator.database;

import android.content.Context;
import android.util.Log;

import com.deliverylocator.entity.DaoMaster;

import org.greenrobot.greendao.database.Database;

public class DbOpenHelper extends DaoMaster.OpenHelper {

    public DbOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        Log.e("oldVersion ", oldVersion + ", newVersion: " + newVersion);

    }
}
