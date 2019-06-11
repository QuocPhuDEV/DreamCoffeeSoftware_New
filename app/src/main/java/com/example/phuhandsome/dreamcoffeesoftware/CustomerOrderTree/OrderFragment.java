package com.example.phuhandsome.dreamcoffeesoftware.CustomerOrderTree;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Account;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.AccountModel;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Accounts.LoginFragment;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Accounts.ManagerSignUpFragment;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Customer.Customer;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.DetailsBill.DetailsBill;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.DetailsBill.DetailsBillInsert;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Employee.EmployeeInsert;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Employee.EmployeeList;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Employee.Empoyee;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.Menu;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.MenuAdapter;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.MenuFragment;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.MenuList;
import com.example.phuhandsome.dreamcoffeesoftware.MainActivity;
import com.example.phuhandsome.dreamcoffeesoftware.R;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {
    private TextView tvCustomerName, tvTableName, tvDate;
    private Button btnPay, btnAddOrder;

    private GridView gvOrderMenu;
    private OrderAdapter orderAdapter;
    private List<Menu> menuDrinkList;

    private DetailsBill detailsBill;
    private DetailsBillInsert detailsBillInsert;

    public static int orderNumber = 0;
    public ArrayList<Integer> listnumberOrder = new ArrayList();

    private Calendar cal;

    public static String customerName = "";

    public static String drinksName;
    public static int drinksPrice;
    public static int drinksNumber;


    public OrderFragment() {
        // Required empty public constructor
    }

    //thêm sự kiện xử lý
    private void AddEvents() {

        // Đổ tên khách hàng đã login.
        try {
            CustomerLoginWithUser customerLoginWithUser = new CustomerLoginWithUser();
            List<Customer> customerList = customerLoginWithUser.getCustomerList();

            for (Customer customer : customerList) {
                tvCustomerName.setText("Khách hàng: " + customer.getCustomername());
                customerName = customer.getCustomername();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        // Đổ dữ liệu đồ uống đã chọn
        try {
            menuDrinkList = new ArrayList<>();
            orderAdapter = new OrderAdapter(getContext(), R.layout.order_items_menu, menuDrinkList);
            gvOrderMenu.setAdapter(orderAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // xử lý gọi thêm đồ uống
        btnAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginFragment loginFragment = new LoginFragment();
                String checkLogin = loginFragment.permissionLogin;
                if (checkLogin.equals("")) {
                    Toast.makeText(getContext(), "Bạn cần login để gọi món!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Nhân viên đã nhận được yêu cầu!", Toast.LENGTH_SHORT).show();

                }
            }
        });


        // Xử lý thanh toán
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BillActivity.class);
                startActivity(intent);
            }
        });
    }

    // thêm dữ liệu vào chuỗi
    private void AddList() {
        try {

            MenuItemSelectedWithDrinks menuList = new MenuItemSelectedWithDrinks();
            //tạo 1 list mới chứa kết quả truy vấn từ sql
            List<Menu> list;
            list = menuList.getMenuList();

            MenuFragment menuFragment = new MenuFragment();
            for (Menu menu : list) {
                menuDrinkList.add(menu);
                orderNumber = menuFragment.orderNumber;

                drinksName = menu.getDrinks();
                drinksPrice = menu.getPrice();
                drinksNumber = orderNumber;

                //String drinks = menu.getDrinks();
                int table = 1;
                //int num = orderNumber;
                detailsBillInsert = new DetailsBillInsert();
                detailsBill = new DetailsBill(table, drinksName, drinksNumber);
                if (detailsBillInsert.Add(detailsBill) == true) {
                    //Toast.makeText(getContext(), "Gọi thành công!", Toast.LENGTH_SHORT).show();
                } else if (detailsBillInsert.Add(detailsBill) == false) {
                    //Toast.makeText(getContext(), "Gọi không thành công!, vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();

        }

    }

    // check login()
    private void CheckLogin() {
        LoginFragment loginFragment = new LoginFragment();
        String checkLogin = loginFragment.permissionLogin;
        if (checkLogin.equals("")) {
            btnPay.setEnabled(false);
            btnPay.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    //lấy ngày giờ hệ thống
    private void GetDate() {
        //Set ngày giờ hiện tại khi mới chạy lần đầu
        cal = Calendar.getInstance();
        SimpleDateFormat dft = null;
        //Định dạng kiểu ngày / tháng /năm
        dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate = dft.format(cal.getTime());
        //hiển thị lên giao diện
        tvDate.setText("Ngày: " + strDate);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        gvOrderMenu = (GridView) view.findViewById(R.id.gvOrder);
        tvCustomerName = (TextView) view.findViewById(R.id.tvcustomerName);
        btnAddOrder = (Button) view.findViewById(R.id.btnAddOrder);
        btnPay = (Button) view.findViewById(R.id.btnPay);
        tvDate = (TextView) view.findViewById(R.id.tvDateOfOrder);

        AddEvents();
        AddList();
        GetDate();
        CheckLogin();
        return view;
    }

}
