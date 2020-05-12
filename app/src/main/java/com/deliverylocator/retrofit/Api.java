package com.deliverylocator.retrofit;

import com.deliverylocator.dto.DeliveryDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by HP on 2/27/2018.
 */


public interface Api {
//    @GET(WebServices.GET_DELIVERIES)
//    Call<List<DeliveryDTO>> getListOfDeliveries();

    @GET(WebServices.GET_DELIVERIES)
    Call<List<DeliveryDTO>> getListOfDeliveries();
}
