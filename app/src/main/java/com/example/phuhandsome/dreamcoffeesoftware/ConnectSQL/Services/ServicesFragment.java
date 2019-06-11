package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Services;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Sale.Sale;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Sale.SaleAdapter;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Sale.SaleList;
import com.example.phuhandsome.dreamcoffeesoftware.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends Fragment {

    private GridView gvServices;
    private ServiceAdapter serviceAdapter;
    private List<Services> servicesList;


    public ServicesFragment() {
        // Required empty public constructor
    }

    private void AddEvents() {
        servicesList = new ArrayList<>();
        serviceAdapter = new ServiceAdapter(getContext(), R.layout.services_items, servicesList);
        gvServices.setAdapter(serviceAdapter);

        try {
            ServiceList serviceList = new ServiceList();
            List<Services> services;
            services = serviceList.getServicesList();

            for (Services service : services) {
                servicesList.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_services, container, false);
        gvServices = (GridView) view.findViewById(R.id.gvServices);

        AddEvents();
        return view;
    }

}
