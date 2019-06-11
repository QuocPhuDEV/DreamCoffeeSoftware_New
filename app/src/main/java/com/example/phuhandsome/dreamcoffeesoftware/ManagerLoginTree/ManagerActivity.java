package com.example.phuhandsome.dreamcoffeesoftware.ManagerLoginTree;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Employee.EmployeeFragment;
import com.example.phuhandsome.dreamcoffeesoftware.FactoryManager.FactoryManagerFragment;
import com.example.phuhandsome.dreamcoffeesoftware.HomeFragment;
import com.example.phuhandsome.dreamcoffeesoftware.R;

public class ManagerActivity extends AppCompatActivity {
    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        AddObject();
        Bottom_Nav();
    }

    private void AddObject() {
        mMainNav = (BottomNavigationView) findViewById(R.id.Nav_Bottom_Manager);
        mMainFrame = (FrameLayout) findViewById(R.id.main_manager_frame);
    }

    private void Bottom_Nav() {
        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_QLNV:
                        setFragment(new EmployeeFragment());
                        return true;

                    case R.id.nav_QLKho:
                        setFragment(new FactoryManagerFragment());
                        return true;

                    case R.id.nav_QLThongKe:
                        setFragment(new StatisticalFragment());
                        return true;
                    default:
                        return true;
                }
            }
        });
    }

    // cài đặt fragment
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_manager_frame, fragment);
        fragmentTransaction.commit();
    }

    // Sự kiện Click Back trở về màn hình chính
    boolean dClick = false;

    @Override
    public void onBackPressed() {
        // Trở về màn hình chính
        setFragment(new HomeFragment());

        // Click lần nữa để thoát ứng dụng
        if (dClick) {
            super.onBackPressed();
            return;
        }
        this.dClick = true;
        Toast.makeText(this, "Bấm về Dream Coffee", Toast.LENGTH_SHORT).show();
        // Thời gian chờ click để thoát ứng dụng
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dClick = false;
            }
        }, 1000);
    }
}
