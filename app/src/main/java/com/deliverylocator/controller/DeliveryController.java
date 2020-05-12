package com.deliverylocator.controller;

import android.content.Context;

import com.deliverylocator.DeliveryLocatorApplication;
import com.deliverylocator.entity.DaoSession;
import com.deliverylocator.entity.Delivery;
import com.deliverylocator.entity.DeliveryDao;

import java.util.List;

public class DeliveryController {
    public static List<Delivery> getAll(Context context, int start) {
        DaoSession daoSession = ((DeliveryLocatorApplication) context.getApplicationContext()).getDaoSession();
        List<Delivery> deliveryList = daoSession.queryBuilder(Delivery.class).orderDesc(DeliveryDao.Properties.Id)
                .offset(start).limit(20).build().forCurrentThread().list();
        daoSession.clear();
        return deliveryList;
    }

    public static void saveAll(Context context, List<Delivery> deliveries) {
        DaoSession daoSession = ((DeliveryLocatorApplication) context.getApplicationContext()).getDaoSession();
        daoSession.getDeliveryDao().insertOrReplaceInTx(deliveries);
        daoSession.clear();
    }

    public static long getCount(Context context) {
        DaoSession daoSession = ((DeliveryLocatorApplication) context.getApplicationContext()).getDaoSession();
        long count = daoSession.queryBuilder(Delivery.class).count();
        daoSession.clear();
        return count;
    }
}
