package com.deliverylocator.views.activities;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.deliverylocator.R;
import com.deliverylocator.util.PermissionUtility;
import com.deliverylocator.views.fragments.DeliveryListFragment;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);
        ButterKnife.bind(this);
        PermissionUtility.grandAllPermission(this);
        replaceFragment(new DeliveryListFragment(), "deliverylist");
    }

    public void replaceFragment(Fragment fragment, String tag) {
        FrameLayout fl = findViewById(R.id.container_main);
        fl.removeAllViewsInLayout();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_main, fragment, tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        int fragments = fragmentManager.getBackStackEntryCount();
        if (fragments == 1) {
            finish();
        } else if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
