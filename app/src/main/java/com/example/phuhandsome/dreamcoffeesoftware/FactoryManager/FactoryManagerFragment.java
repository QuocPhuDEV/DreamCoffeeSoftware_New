package com.example.phuhandsome.dreamcoffeesoftware.FactoryManager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Employee.EmployeeAdapter;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Employee.Empoyee;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.MenuAdapter;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.MenuList;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.Menu;
import com.example.phuhandsome.dreamcoffeesoftware.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FactoryManagerFragment extends Fragment {
    private GridView gvMenu;
    private FactoryAdapter factoryAdapter;
    private List<Menu> menuList;


    public FactoryManagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        try {
            View view = inflater.inflate(R.layout.fragment_factory_manager, container, false);
            gvMenu = view.findViewById(R.id.gvFactory);

            AddEvent();
            AddList();
            // Inflate the layout for this fragment
            return view;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void AddEvent() {
        try {
            menuList = new ArrayList<>();
            factoryAdapter = new FactoryAdapter(getContext(), R.layout.factory_menu_items, menuList);
            gvMenu.setAdapter(factoryAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // thêm dữ liệu vào chuỗi
    private void AddList() {

        try {
            // gọi class truy vấn sql
            MenuList menuList = new MenuList();
            //tạo 1 list mới chứa kết quả truy vấn từ sql
            List<Menu> list;
            list = menuList.getMenuList();
            // duyệt mảng list vừa có để lấy kết quả truyền vào menuDrinkList để truyền vào adapter
            for (Menu menu : list) {
                this.menuList.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
        }

    }

}
