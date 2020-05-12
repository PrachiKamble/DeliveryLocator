package com.deliverylocator.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.deliverylocator.R;
import com.deliverylocator.controller.DeliveryController;
import com.deliverylocator.dto.DeliveryDTO;
import com.deliverylocator.dto.LocationDTO;
import com.deliverylocator.entity.Delivery;
import com.deliverylocator.helper.DtoToEntityConverter;
import com.deliverylocator.retrofit.Api;
import com.deliverylocator.retrofit.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {
    Api api = ServiceGenerator.getApi();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        long count = DeliveryController.getCount(this);
        getAllDataFromServer();
//        if (count > 1L) {
//            new Handler().postDelayed(() -> {
//                moveToMain();
//            }, 500);
//        } else {
//            getAllDataFromServer();
//        }
    }

    private void getAllDataFromServer() {
        Call<List<DeliveryDTO>> call = api.getListOfDeliveries();
        call.enqueue(new Callback<List<DeliveryDTO>>() {
            @Override
            public void onResponse(Call<List<DeliveryDTO>> call, Response<List<DeliveryDTO>> response) {
                if (response.code() == 200) {
                    List<DeliveryDTO> deliveries = response.body();
                    List<Delivery> deliveryList = new ArrayList<>();
                    if (deliveries != null && !deliveries.isEmpty()) {
                        for (DeliveryDTO deliveryDTO : deliveries) {
                            LocationDTO locationDTO = deliveryDTO.getLocation();
                            if (locationDTO != null) {
                                deliveryDTO.setLat(locationDTO.getLat());
                                deliveryDTO.setLng(locationDTO.getLng());
                                deliveryDTO.setAddress(locationDTO.getAddress());
                            }
                            Log.e("onResponse: ", deliveryDTO.toString());
                            deliveryList.add(DtoToEntityConverter.getDelivery(deliveryDTO));
                        }
                        DeliveryController.saveAll(SplashScreenActivity.this, deliveryList);
                        moveToMain();
                    } else {
                        Toast.makeText(SplashScreenActivity.this, R.string.server_error, Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DeliveryDTO>> call, Throwable t) {
                Log.e("onFailure: ", t.getMessage() + " ");
                Toast.makeText(SplashScreenActivity.this, R.string.server_error, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void moveToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
