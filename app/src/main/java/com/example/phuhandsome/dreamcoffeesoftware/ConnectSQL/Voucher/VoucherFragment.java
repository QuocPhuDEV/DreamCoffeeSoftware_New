package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Voucher;


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
public class VoucherFragment extends Fragment {
    private GridView gvVoucher;
    private VoucherAdapter voucherAdapter;
    private List<Voucher> voucherList;


    public VoucherFragment() {
        // Required empty public constructor
    }


    private void AddEvents() {
        voucherList = new ArrayList<>();
        voucherAdapter = new VoucherAdapter(getContext(), R.layout.voucher_items, voucherList);
        gvVoucher.setAdapter(voucherAdapter);

        try {
            VoucherList voucherList = new VoucherList();
            List<Voucher> vouchers;
            vouchers = voucherList.getVoucherList();

            for (Voucher voucher : vouchers) {
                this.voucherList.add(voucher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_voucher, container, false);
        gvVoucher = (GridView) view.findViewById(R.id.gvVoucher);

        AddEvents();
        return view;
    }

}
