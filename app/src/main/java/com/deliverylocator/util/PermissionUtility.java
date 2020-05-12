package com.deliverylocator.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Created by uday on 08-06-18.
 */

public class PermissionUtility {

    private static final int ASK_MULTIPLE_PERMISSION_REQUEST_CODE = 1;
    public static final int REQUEST_FINE_LOCATION_PERMISSIONS = 4;
    private static final int REQUEST_COARSE_LOCATION_PERMISSIONS = 5;

    public static void grandAllPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION},
                ASK_MULTIPLE_PERMISSION_REQUEST_CODE);
    }

    public static boolean checkFineLocationPermission(Activity activity) {
        int smsPermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        if (smsPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOCATION_PERMISSIONS);
            return false;
        }
        return true;
    }

    public static boolean checkCoarseLocationPermission(Activity activity) {
        int smsPermission = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (smsPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_COARSE_LOCATION_PERMISSIONS);
            return false;
        }
        return true;
    }
}