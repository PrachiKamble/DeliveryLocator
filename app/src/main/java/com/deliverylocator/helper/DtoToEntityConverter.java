package com.deliverylocator.helper;

import com.deliverylocator.dto.DeliveryDTO;
import com.deliverylocator.entity.Delivery;

public class DtoToEntityConverter {
    public static Delivery getDelivery(DeliveryDTO deliveryDTO) {
        Delivery delivery = new Delivery();
        delivery.setId(deliveryDTO.getId());
        delivery.setImageUrl(deliveryDTO.getImageUrl());
        delivery.setDescription(deliveryDTO.getDescription());
        delivery.setLat(deliveryDTO.getLat());
        delivery.setLng(deliveryDTO.getLng());
        delivery.setAddress(deliveryDTO.getAddress());
        return delivery;
    }
}
