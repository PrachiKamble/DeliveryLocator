package com.deliverylocator.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.json.JSONObject;

@Entity
public class Delivery {
    @Id(autoincrement = false)
    private long id;
    private String description;
    private String imageUrl;
    private double lat;
    private double lng;
    private String address;
//    private transient JSONObject location;
//    private Location location;

    @Generated(hash = 1199224230)
    public Delivery(long id, String description, String imageUrl, double lat,
            double lng, String address) {
        this.id = id;
        this.description = description;
        this.imageUrl = imageUrl;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
    }

    @Generated(hash = 182838443)
    public Delivery() {
    }

    @Override
    public String toString() {
        return "DeliveryDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", address='" + address + '\'' +
                '}';
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return this.lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
