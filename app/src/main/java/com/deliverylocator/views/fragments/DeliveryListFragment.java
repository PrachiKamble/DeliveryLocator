package com.deliverylocator.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.deliverylocator.R;
import com.deliverylocator.controller.DeliveryController;
import com.deliverylocator.entity.Delivery;
import com.deliverylocator.util.Constants;
import com.deliverylocator.views.adapters.DeliveryListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeliveryListFragment extends Fragment {
    @BindView(R.id.recyclerDeliveryList)
    RecyclerView recyclerDeliveryList;
    private LinearLayoutManager linearLayoutManager;
    private DeliveryListAdapter deliveryListAdapter;
    private List<Delivery> deliveryList;
    private Long totalRecordCount;
    private int page = 2;
    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_delivery_list, container, false);
        ButterKnife.bind(this, view);
        if (getActivity() != null) {
            context = getActivity();
        }
        totalRecordCount = DeliveryController.getCount(context);
        loadFragmentDetails();
        addOnScrollListener();
        return view;
    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }

    private void loadFragmentDetails() {
        deliveryList = DeliveryController.getAll(context, 0);
        recyclerDeliveryList.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerDeliveryList.setLayoutManager(linearLayoutManager);
        deliveryListAdapter = new DeliveryListAdapter(getActivity(), deliveryList);
        recyclerDeliveryList.setAdapter(deliveryListAdapter);
        deliveryListAdapter.notifyDataSetChanged();
        page = 2;
    }

    private void addOnScrollListener() {
        recyclerDeliveryList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = recyclerDeliveryList.getChildCount();
                int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                int totalItemCount = linearLayoutManager.getItemCount();
                final int lastItem = firstVisibleItem + visibleItemCount;
                if (dy > 0) {
                    if (lastItem == totalItemCount) {
                        long end = Constants.RECORD_LOADING_LIMIT * page;
                        long start = end - (Constants.RECORD_LOADING_LIMIT - 1);
                        if (end > totalRecordCount) {
                            end = totalRecordCount;
                        }
                        if (start < end) {
                            deliveryList.addAll(DeliveryController.getAll(context, (int) start));
                            deliveryListAdapter.notifyDataSetChanged();
                            page++;
                        }
                    }
                }
            }
        });
    }
}
