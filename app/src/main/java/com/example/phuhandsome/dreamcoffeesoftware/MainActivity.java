package com.example.phuhandsome.dreamcoffeesoftware;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Accounts.LoginFragment;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Accounts.ManagerSignUpFragment;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Accounts.SignUpFragment;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.DetailsBill.DetailsBillFragment;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.MenuAdapter;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.MenuFragment;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.MenuList;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Sale.SaleFragment;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Services.ServicesFragment;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Voucher.VoucherFragment;
import com.example.phuhandsome.dreamcoffeesoftware.CustomerOrderTree.OrderFragment;
import com.example.phuhandsome.dreamcoffeesoftware.ManagerLoginTree.ManagerActivity;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    private BottomNavigationView mMainNav;
    private NavigationView navigationView;
    private FrameLayout mMainFrame;

    private LoginFragment loginFragment;
    private SignUpFragment signUpFragment;
    private MenuFragment menuFragment;
    private ManagerSignUpFragment managerSignUpFragment;

    private TextView tvCustomerName;
    private TextView tvCustomerEmail;
    private ImageView imgAvatar;

    public String userPermisson = "";

    private boolean dClick = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddObject();
        AddNavigation();
        AddEvents();
        Draer_Nav(navigationView);
        Bottom_Nav();
        Token();

    }

    //gọi sự kiện onBackPressed
    @Override
    public void onBackPressed() {

        // Trở về màn hình chính
        setFragment(new HomeFragment());

        // Click lần nữa để thoát ứng dụng
        if (dClick) {
            super.onBackPressed();
            return;
        }
        dClick = true;

        // Thời gian chờ click để thoát ứng dụng
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dClick = false;
            }
        }, 1000000000);
    }

    // Ánh xạ
    private void AddObject() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);


        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.Nav_Bottom);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        loginFragment = new LoginFragment();
        signUpFragment = new SignUpFragment();

    }

    // gọi navigation bar
    private void AddNavigation() {
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LoadItems();
    }

    // Kiểm tra xem loại account nào để ẩn items signin
    private void LoadItems() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        LoginFragment loginFragment = new LoginFragment();
        userPermisson = loginFragment.permissionLogin;
        Menu menu = navigationView.getMenu();
        for (int menuItemIndex = 0; menuItemIndex < menu.size(); menuItemIndex++) {
            MenuItem menuItem = menu.getItem(menuItemIndex);
            if (userPermisson != "") {
                if (menuItem.getItemId() == R.id.itemLogin) {
                    menuItem.setVisible(false);
                }
            }
            if (userPermisson.equals("customer")) {
                if (menuItem.getItemId() == R.id.itemSignUp) {
                    menuItem.setVisible(false);
                }
            }
            if (userPermisson.equals("manager")) {
                if (menuItem.getItemId() == R.id.itemManager) {
                    menuItem.setVisible(true);
                }
                if (menuItem.getItemId() == R.id.itemMenuSelected) {
                    menuItem.setVisible(false);
                }
                if (menuItem.getItemId() == R.id.itemViewOrder) {
                    menuItem.setVisible(true);
                }
            }
            if (userPermisson.equals("employee")) {
                if (menuItem.getItemId() == R.id.itemViewOrder) {
                    menuItem.setVisible(true);
                }
            }
        }
    }

    // Thêm sự kiện click vào các button của navigations
    public void SelectItemDrawer(MenuItem item) {
        Fragment fragment = null;
        Class fragmentClass = null;
        switch (item.getItemId()) {
            case R.id.itemLogin:
                try {
                    fragmentClass = LoginFragment.class;
                    break;
                } catch (Exception e) {
                }
            case R.id.itemSignUp:
                try {
                    LoginFragment loginFragment = new LoginFragment();
                    userPermisson = loginFragment.permissionLogin;
                    if (userPermisson.equals("manager")) {
                        fragmentClass = ManagerSignUpFragment.class;
                    } else {
                        fragmentClass = SignUpFragment.class;
                    }
                    break;
                } catch (Exception e) {
                }
            case R.id.itemMenuSelected:
                try {
                    fragmentClass = OrderFragment.class;
                    break;
                } catch (Exception e) {
                }
            case R.id.itemViewOrder:
                try {
                    fragmentClass = DetailsBillFragment.class;
                    break;
                } catch (Exception e) {
                }
            case R.id.itemListDrinks:
                try {
                    fragmentClass = MenuFragment.class;
                    break;
                } catch (Exception e) {
                }
            case R.id.itemSale:
                try {
                    fragmentClass = SaleFragment.class;
                    break;
                } catch (Exception e) {
                }
            case R.id.itemVoucher:
                try {
                    fragmentClass = VoucherFragment.class;
                    break;
                } catch (Exception e) {
                }
            case R.id.itemService:
                try {
                    fragmentClass = ServicesFragment.class;
                    break;
                } catch (Exception e) {
                }
            case R.id.itemManager:
                /*try {
                    fragmentClass = ManagerFragment.class;
                    break;
                } catch (Exception e) {
                }*/
                Intent intent = new Intent(getApplicationContext(), ManagerActivity.class);
                startActivity(intent);
            case R.id.itemLogout:
                System.exit(0);
                break;
            case R.id.itemExit:
                finish();
                break;
            default:
                try {
                    fragmentClass = HomeFragment.class;
                } catch (Exception e) {
                }

        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception ex) {
            ex.getMessage();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_frame, fragment).commit();
        setTitle(item.getTitle());
        drawerLayout.closeDrawer(Gravity.START);
    }

    // Xử lý click drawer navigation
    private void Draer_Nav(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                SelectItemDrawer(item);
                return true;
            }
        });
    }

    // Xử lý click bottom navigation
    private void Bottom_Nav() {
        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_Home:
                        /*ReloadMain();*/
                        try {
                            setFragment(new HomeFragment());
                        } catch (Exception e) {
                            e.getMessage();
                        }
                        return true;

                    case R.id.nav_Menu:
                        try {
                            setFragment(new MenuFragment());
                        } catch (Exception e) {
                            e.getMessage();
                        }
                        return true;

                    case R.id.nav_Sale:

                        try {
                            setFragment(new SaleFragment());
                        } catch (Exception e) {
                            e.getMessage();
                        }
                        return true;

                    case R.id.nav_Voucher:
                        try {
                            setFragment(new VoucherFragment());
                        } catch (Exception e) {
                            e.getMessage();
                        }
                        return true;

                    case R.id.nav_Services:
                        try {
                            setFragment(new ServicesFragment());
                        } catch (Exception e) {
                            e.getMessage();
                        }
                        return true;

                    default:
                        try {
                            setFragment(new HomeFragment());
                        } catch (Exception e) {
                            e.getMessage();
                        }
                        return true;
                }
            }
        });
    }

    // cài đặt fragment
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    // Thêm sự kiện xử lý
    private void AddEvents() {

        //Xử lý sau khi login
        String user = LoginFragment.userNameLogin;
        if (user != null) {
            View headerView = navigationView.getHeaderView(0);
            tvCustomerName = (TextView) headerView.findViewById(R.id.nameCustomer);
            tvCustomerEmail = (TextView) headerView.findViewById(R.id.emailCustomer);
            imgAvatar = (ImageView) headerView.findViewById(R.id.imgAvatar);

            if (user != null) {
                tvCustomerName.setText("Hoàng Quốc Phú");
                tvCustomerEmail.setText("Phuhq95@gmail.com");
                imgAvatar.setImageResource(R.drawable.avatar);
            }
        }

    }

    //Reload main
    private void ReloadMain() {
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    // gọi menu_main lên
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // xử lý click change language
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemChangeLanguage:
                if (item.getTitle().equals("VietNam")) {
                    Toast.makeText(this, "Bạn đã chọn tiếng việt!", Toast.LENGTH_SHORT).show();
                    AddLanguage("vi-rVN");
                    item.setIcon(R.drawable.en);
                    item.setTitle("English");

                    //add 2 phương thức của drawer navigation lại
                    AddObject();
                    AddNavigation();
                } else {
                    Toast.makeText(this, "You are select English", Toast.LENGTH_SHORT).show();
                    AddLanguage("en");
                    item.setIcon(R.drawable.vn);
                    item.setTitle("VietNam");

                    //add 2 phương thức của drawer navigation lại
                    AddObject();
                    AddNavigation();
                }
                break;
        }

        //gọi sự kiện click vào drawer navigation
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // phương thức truyền ngôn ngữ
    public void AddLanguage(String language) {
        Locale locale = new Locale(language);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        //load lại main chính
        setContentView(R.layout.activity_main);
    }

    // Gọi token
    public void Token() {
        FirebaseMessaging.getInstance().subscribeToTopic("testfcm");
        String token = FirebaseInstanceId.getInstance().getToken();
        new FireBaseIDTask().execute(token);
    }

}
