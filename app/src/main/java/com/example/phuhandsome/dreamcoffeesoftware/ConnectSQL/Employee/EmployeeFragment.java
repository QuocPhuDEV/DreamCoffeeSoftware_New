package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Employee;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.Menu;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.MenuAdapter;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.MenuList;
import com.example.phuhandsome.dreamcoffeesoftware.ManagerLoginTree.EmployeeInforActivity;
import com.example.phuhandsome.dreamcoffeesoftware.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmployeeFragment extends Fragment {
    private GridView gvEmployee;
    private EmployeeAdapter employeeAdapter;
    private List<Empoyee> empolyeeList;


    public EmployeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        try {
            View view = inflater.inflate(R.layout.fragment_employee, container, false);
            gvEmployee = view.findViewById(R.id.gvEmployee);

            AddEvent();
            AddList();
            ClickItems();

            return view;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void AddEvent() {
        try {
            empolyeeList = new ArrayList<>();
            employeeAdapter = new EmployeeAdapter(getContext(), R.layout.employee_item, empolyeeList);
            gvEmployee.setAdapter(employeeAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // thêm dữ liệu vào chuỗi
    private void AddList() {

        try {
            // gọi class truy vấn sql
            EmployeeList employeeList = new EmployeeList();
            //tạo 1 list mới chứa kết quả truy vấn từ sql
            List<Empoyee> list;
            list = employeeList.getEmployeeList();
            // duyệt mảng list vừa có để lấy kết quả truyền vào menuDrinkList để truyền vào adapter
            for (Empoyee employee : list) {
                empolyeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
        }

    }

    // xử lý sự kiện click vào items của listview
    private void ClickItems() {
        gvEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), EmployeeInforActivity.class);

                Bundle bundle = new Bundle();
                bundle.putInt("idemployee", empolyeeList.get(position).getID_Employee());
                bundle.putInt("idaccount", empolyeeList.get(position).getID_Account());
                bundle.putInt("idposition", empolyeeList.get(position).getID_Position());
                bundle.putString("name", empolyeeList.get(position).getEmployeename());
                bundle.putString("birth", empolyeeList.get(position).getBirthofdate());
                bundle.putString("sex", empolyeeList.get(position).getSex());
                bundle.putString("phone", empolyeeList.get(position).getPhonenumber());
                bundle.putString("email", empolyeeList.get(position).getEmail());
                bundle.putString("address", empolyeeList.get(position).getCusAddress());

                intent.putExtra("employeeInfor", bundle);
                startActivity(intent);
            }
        });
    }

}
