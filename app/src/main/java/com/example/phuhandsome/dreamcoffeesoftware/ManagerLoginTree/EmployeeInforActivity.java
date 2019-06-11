package com.example.phuhandsome.dreamcoffeesoftware.ManagerLoginTree;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.print.PrinterId;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Employee.EmployeeDelete;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Employee.EmployeeFragment;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Employee.EmployeeInsert;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Employee.EmployeeList;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Employee.EmployeeUpdate;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Employee.Empoyee;
import com.example.phuhandsome.dreamcoffeesoftware.HomeFragment;
import com.example.phuhandsome.dreamcoffeesoftware.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeInforActivity extends AppCompatActivity {
    private EditText edEmployeeName, edBirthofdate, edsex, edPhonenumber, edEmail, edCusAddress;
    private Button btnAdd, btnEdit, btnDelete;

    private EmployeeUpdate employeeUpdate;
    private EmployeeDelete employeeDelete;
    private EmployeeInsert employeeInsert;
    private Empoyee employee;

    public static int ID_Employee;
    public static int ID_Account;
    public static int ID_Position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_infor);
        AddObjects();
        AddEvents();
    }

    private void AddObjects() {
        edEmployeeName = (EditText) findViewById(R.id.edEmployeeName);
        edBirthofdate = (EditText) findViewById(R.id.edBirthofdate);
        edsex = (EditText) findViewById(R.id.edsex);
        edPhonenumber = (EditText) findViewById(R.id.edPhonenumber);
        edEmail = (EditText) findViewById(R.id.edEmail);
        edCusAddress = (EditText) findViewById(R.id.edCusAddress);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnDelete = (Button) findViewById(R.id.btnXoa);
    }

    private void AddEvents() {
        setData();
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisnableButtonAdd();
                DisnableButtonDelete();

                if (btnEdit.getText().equals("Sửa")) {
                    EnableEditText();
                    btnEdit.setText("Lưu");

                } else if (btnEdit.getText().equals("Lưu")) {
                    if (CheckEditText() == true) {
                    } else {
                        try {
                            UpdateData();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        /*EnableButtonAdd();
                        EnableButtonDelete();
                        DisnableEditText();
                        btnEdit.setText("Sửa");*/
                    }
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteData();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnAdd.getText().equals("Thêm")) {
                    btnAdd.setText("Lưu");
                    EnableEditText();
                    ClearEditText();
                    DisnableButtonDelete();
                    DisnableButtonUpdate();
                    SetTextHint();
                } else if (btnAdd.getText().equals("Lưu")) {
                    if (CheckEditText() == false) {
                        InsertData();
                        ClearEditText();
                    } else {

                    }

                }
            }
        });

    }

    // Sửa dữ liệu
    private void UpdateData() {
        try {
            int idemployee = ID_Employee;
            String name = edEmployeeName.getText().toString();
            String birth = edBirthofdate.getText().toString();
            String sex = edsex.getText().toString();
            String phone = edPhonenumber.getText().toString();
            String email = edEmail.getText().toString();
            String address = edCusAddress.getText().toString();
            employeeUpdate = new EmployeeUpdate();
            employee = new Empoyee(idemployee, 0, 0, name, birth, sex, phone, email, address);

            if (employeeUpdate.Update(employee) == true) {
                Toast.makeText(this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
            } else if (employeeUpdate.Update(employee) == false) {
                Toast.makeText(this, "Sửa không thành công!, vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

    }

    // Xóa dữ liệu
    private void DeleteData() {
        try {
            int idemployee = ID_Employee;

            employeeDelete = new EmployeeDelete();
            employee = new Empoyee(idemployee, 0, 0, null, null, null, null, null, null);

            if (employeeDelete.Delete(employee) == true) {
                Toast.makeText(this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
            } else if (employeeDelete.Delete(employee) == false) {
                Toast.makeText(this, "Xóa không thành công!, vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

    }

    // Xóa dữ liệu
    private void InsertData() {
        try {

            // kiểm tra xem trong bảng đã có bao nhiêu nhân viên
            int countEmployee = 0;
            EmployeeList employeeList = new EmployeeList();
            List<Empoyee> employees = new ArrayList<>();
            employees = employeeList.getEmployeeList();
            for (Empoyee empoyeess : employees) {
                countEmployee = empoyeess.getID_Employee();
            }

            // đổ dữ liệu vào class Employee
            int idemployee = countEmployee + 1;
            int idAccount = ID_Account;
            int idPosition = ID_Position;
            String name = edEmployeeName.getText().toString();
            String birth = edBirthofdate.getText().toString();
            String sex = edsex.getText().toString();
            String phone = edPhonenumber.getText().toString();
            String email = edEmail.getText().toString();
            String address = edCusAddress.getText().toString();

            // thực hiện insert
            employeeInsert = new EmployeeInsert();
            employee = new Empoyee(idemployee, idAccount, idPosition, name, birth, sex, phone, email, address);

            // báo kết quả
            if (employeeInsert.Add(employee) == true) {
                Toast.makeText(this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
            } else if (employeeInsert.Add(employee) == false) {
                Toast.makeText(this, "Thêm không thành công!, vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

    }

    // truyền dữ liệu từ EmployeeFragment qua
    private void setData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("employeeInfor");
        edEmployeeName.setText(bundle.getString("name"));
        edBirthofdate.setText(bundle.getString("birth"));
        edsex.setText(bundle.getString("sex"));
        edPhonenumber.setText(bundle.getString("phone"));
        edEmail.setText(bundle.getString("email"));
        edCusAddress.setText(bundle.getString("address"));
        ID_Employee = bundle.getInt("idemployee");
        ID_Account = bundle.getInt("idaccount");
        ID_Position = bundle.getInt("idposition");
    }

    // Set thuộc tính enable cho các editText
    private void EnableEditText() {
        edEmployeeName.setEnabled(true);
        edBirthofdate.setEnabled(true);
        edsex.setEnabled(true);
        edPhonenumber.setEnabled(true);
        edEmail.setEnabled(true);
        edCusAddress.setEnabled(true);
    }

    // clear editText
    private void ClearEditText() {
        edEmployeeName.setText("");
        edBirthofdate.setText("");
        edsex.setText("");
        edPhonenumber.setText("");
        edEmail.setText("");
        edCusAddress.setText("");
    }

    // set text hint cho EditText khi thêm
    private void SetTextHint() {
        edEmployeeName.setHint("Nhập tên nhân viên");

        edBirthofdate.setHint("Nhập ngày sinh nhân viên");

        edsex.setHint("Nhập giới tính nhân viên");

        edPhonenumber.setHint("Nhập số điện thoại");

        edEmail.setHint("Nhập Email nhân viên");

        edCusAddress.setHint("Nhập địa chỉ nhân viên");
    }

    // Set thuộc tính Disnable cho các editText
    private void DisnableEditText() {
        edEmployeeName.setEnabled(false);
        edBirthofdate.setEnabled(false);
        edsex.setEnabled(false);
        edPhonenumber.setEnabled(false);
        edEmail.setEnabled(false);
        edCusAddress.setEnabled(false);
    }

    // check các ràng buộc của editText
    private boolean CheckEditText() {
        String name = edEmployeeName.getText().toString();
        String birth = edBirthofdate.getText().toString();
        String sex = edsex.getText().toString();
        String phone = edPhonenumber.getText().toString();
        String email = edEmail.getText().toString();
        String address = edCusAddress.getText().toString();
        if (name.equals("") || birth.equals("") || sex.equals("") || phone.equals("") || email.equals("") || address.equals("")) {
            edEmployeeName.setHint("Không được phép bỏ trống tên");
            edEmployeeName.setHintTextColor(Color.RED);

            edBirthofdate.setHint("Không được phép bỏ trống ngày sinh");
            edBirthofdate.setHintTextColor(Color.RED);

            edsex.setHint("Không được phép bỏ trống giới tính");
            edsex.setHintTextColor(Color.RED);

            edPhonenumber.setHint("Không được phép bỏ trống số điện thoại");
            edPhonenumber.setHintTextColor(Color.RED);

            edEmail.setHint("Không được phép bỏ trống email");
            edEmail.setHintTextColor(Color.RED);

            edCusAddress.setHint("Không được phép bỏ trống địa chỉ");
            edCusAddress.setHintTextColor(Color.RED);

            return true;
        } else {
            return false;
        }
    }

    // Disnable buttom khi không dùng
    private void DisnableButtonAdd() {
        btnAdd.setEnabled(false);
        btnAdd.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    private void DisnableButtonUpdate() {
        btnEdit.setEnabled(false);
        btnEdit.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    private void DisnableButtonDelete() {
        btnDelete.setEnabled(false);
        btnDelete.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    // Enable buttom khi  dùng
    private void EnableButtonAdd() {
        btnAdd.setEnabled(true);
        btnAdd.setTextColor(getResources().getColor(R.color.white));
    }

    private void EnableButtonUpdate() {
        btnEdit.setEnabled(true);
        btnEdit.setTextColor(getResources().getColor(R.color.white));
    }

    private void EnableButtonDelete() {
        btnDelete.setEnabled(true);
        btnDelete.setTextColor(getResources().getColor(R.color.white));
    }

    // Sự kiện Click Back trở về màn hình chính
    boolean dClick = false;

    @Override
    public void onBackPressed() {
        try {
            // Trở về màn hình chính
            setFragment(new HomeFragment());

            // Click lần nữa để thoát ứng dụng
            if (dClick) {
                super.onBackPressed();
                return;
            }
            this.dClick = true;
            Toast.makeText(this, "Bấm để về Dream Coffee", Toast.LENGTH_SHORT).show();
            // Thời gian chờ click để thoát ứng dụng
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dClick = false;
                }
            }, 1000);
        } catch (Exception e) {
        }
    }

    // cài đặt fragment
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_manager_frame, fragment);
        fragmentTransaction.commit();
    }
}
