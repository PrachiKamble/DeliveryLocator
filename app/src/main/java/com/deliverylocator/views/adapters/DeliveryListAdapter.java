package com.deliverylocator.views.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.deliverylocator.R;
import com.deliverylocator.entity.Delivery;
import com.deliverylocator.util.PermissionUtility;
import com.deliverylocator.views.activities.MainActivity;
import com.deliverylocator.views.fragments.DeliveryLocatorFragment;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by HP on 5/30/2018.
 */

public class DeliveryListAdapter extends RecyclerView.Adapter<DeliveryListAdapter.ViewHolder> {
    private Activity activity;
    private List<Delivery> deliveryList;


    public DeliveryListAdapter(Activity activity, List<Delivery> deliveryList) {
        this.activity = activity;
        this.deliveryList = deliveryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_delivery_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Delivery delivery = deliveryList.get(position);
        holder.tvName.setText(delivery.getDescription());

        if (delivery.getAddress() != null && !delivery.getAddress().isEmpty()) {
            holder.tvAddress.setText(delivery.getAddress());
            holder.tvAddress.setVisibility(View.VISIBLE);
        } else {
            holder.tvAddress.setVisibility(View.GONE);
        }

        if (delivery.getImageUrl() != null && !delivery.getImageUrl().isEmpty()) {
            Glide.with(activity).load(delivery.getImageUrl()).into(holder.deliveryImage);
        } else {
            holder.deliveryImage.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.user));
        }

        holder.customerLayout.setOnClickListener(view -> {
            if (PermissionUtility.checkCoarseLocationPermission(activity) && PermissionUtility.checkFineLocationPermission(activity)) {
                Bundle bundle = new Bundle();
                bundle.putDouble("lat", delivery.getLat());
                bundle.putDouble("lang", delivery.getLng());
                DeliveryLocatorFragment deliveryLocatorFragment = new DeliveryLocatorFragment();
                deliveryLocatorFragment.setArguments(bundle);
                ((MainActivity) activity).replaceFragment(deliveryLocatorFragment, "map");
            }
        });
    }

    @Override
    public int getItemCount() {
        return deliveryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvAddress;
        LinearLayout customerLayout;
        CircleImageView deliveryImage;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            customerLayout = itemView.findViewById(R.id.customerLayout);
            deliveryImage = itemView.findViewById(R.id.profile_img);
        }
    }
}
