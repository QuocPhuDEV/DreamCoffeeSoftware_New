package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Sale;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.MenuAdapter;
import com.example.phuhandsome.dreamcoffeesoftware.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleFragment extends Fragment {
    private GridView gvSale;
    private SaleAdapter saleAdapter;
    private List<Sale> saleList;


    public SaleFragment() {
        // Required empty public constructor
    }

    private void AddEvents() {
        saleList = new ArrayList<>();
        saleAdapter = new SaleAdapter(getContext(), R.layout.sale_items, saleList);
        gvSale.setAdapter(saleAdapter);

        try {
            SaleList saleListSQL = new SaleList();
            List<Sale> sales;
            sales = saleListSQL.getSaleList();

            for (Sale sale : sales) {
                saleList.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sale, container, false);
        gvSale = (GridView) view.findViewById(R.id.gvSale);

        AddEvents();
        return view;
    }

}
