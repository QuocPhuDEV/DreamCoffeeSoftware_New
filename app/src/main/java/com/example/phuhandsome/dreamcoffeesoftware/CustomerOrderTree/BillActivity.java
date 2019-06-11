package com.example.phuhandsome.dreamcoffeesoftware.CustomerOrderTree;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Account;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.AccountModel;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Accounts.ManagerSignUpFragment;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Customer.Customer;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.DetailsBill.DetailsBill;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.DetailsBill.DetailsBillDelete;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.DetailsBill.DetailsBillInsert;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Employee.EmployeeDelete;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Employee.Empoyee;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.Menu;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.MenuAdapter;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.MenuFragment;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.MenuList;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Request.Request;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Request.RequestInsert;
import com.example.phuhandsome.dreamcoffeesoftware.R;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class BillActivity extends AppCompatActivity {
    private TextView tvCustomerName, tvDate, tvTable, tvTotalBill, tvTotal;
    private Button btnPay;

    private BillAdapter billAdapter;
    private ListView lvMenu;
    private List<Menu> menuList;

    private DetailsBill detailsBill;
    private DetailsBillDelete detailsBillDelete;

    private int orderNumber = 0;
    private int totalBill = 0;

    private Calendar cal;

    // thêm đối tượng
    private void AddObject() {
        tvCustomerName = (TextView) findViewById(R.id.tvcustomerName);
        tvDate = (TextView) findViewById(R.id.tvDateOfOrder);
        tvTable = (TextView) findViewById(R.id.tvtableName);
        tvTotalBill = (TextView) findViewById(R.id.tvTotalBill);
        btnPay = (Button) findViewById(R.id.btnPay);
        lvMenu = (ListView) findViewById(R.id.lvItemMenuSelected);

    }

    private void AddList() {
        MenuFragment menuFragment = new MenuFragment();
        orderNumber = menuFragment.orderNumber;

        if (orderNumber != 0) {
            try {
                // gọi class truy vấn sql
                MenuItemSelectedWithDrinks menuList = new MenuItemSelectedWithDrinks();
                //tạo 1 list mới chứa kết quả truy vấn từ sql
                List<Menu> list;
                list = menuList.getMenuList();
                // duyệt mảng list vừa có để lấy kết quả truyền vào menuDrinkList để truyền vào adapter
                for (Menu menu : list) {
                    this.menuList.add(menu);
                }


            } catch (SQLException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // thêm sự kiện xử lý
    private void AddEvents() {

        // Lấy giá trị từ form OrderFragment
        OrderFragment orderFragment = new OrderFragment();
        tvCustomerName.setText("Họ tên khách hàng: " + orderFragment.customerName);
        GetDate();

        // đưa giá trị vào list tính
        try {
            menuList = new ArrayList<>();
            billAdapter = new BillAdapter(getApplicationContext(), R.layout.menu_items_bill, menuList);
            lvMenu.setAdapter(billAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    RequestInsert requestInsert = new RequestInsert();
                    Request request = new Request(1, "true");
                    if (requestInsert.Add(request) == true) {
                        Toast.makeText(BillActivity.this, "Vui lòng chờ, nhân viên đang tới!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BillActivity.this, "Hệ thống đang bận, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }


    private void GetMoney() {
        try {
            MenuItemSelectedWithDrinks menuList = new MenuItemSelectedWithDrinks();
            //tạo 1 list mới chứa kết quả truy vấn từ sql
            List<Menu> list;
            list = menuList.getMenuList();
            // duyệt mảng list vừa có để lấy kết quả
            MenuFragment menuFragment = new MenuFragment();
            orderNumber = menuFragment.orderNumber;
            for (Menu menu : list) {
                totalBill += (menu.getPrice() * orderNumber);
            }
            tvTotalBill.setText("Tổng tiền cần thanh toán: " + String.valueOf(totalBill) + " vnđ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // lấy ngày tháng
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        AddObject();
        AddEvents();
        AddList();
        GetMoney();

    }
}
