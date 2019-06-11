package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Accounts;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Account;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Accounts.ManagerSignUpAccount;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.SignUpAccount;
import com.example.phuhandsome.dreamcoffeesoftware.R;

import java.sql.SQLException;


/**
 * A simple {@link Fragment} subclass.
 */
public class ManagerSignUpFragment extends Fragment {
    Button btnSignUp, btnCancel;
    EditText edUser, edPass, edConfirm;
    AutoCompleteTextView autoPermisson;


    public ManagerSignUpFragment() {
        // Required empty public constructor
    }

    private void ClearEditText() {
        edUser.setText(null);
        edPass.setText(null);
        edConfirm.setText(null);
        autoPermisson.setText(null);
    }

    // Đưa dữ liệu vào autocomplete
    private void AutoComplete() {
        // Lấy danh sách dữ liệu từ file values/permisson.xml
        final String[] list = getResources().getStringArray(R.array.arrPermisson);
        //Đưa dữ liệu danh sách đã tạo vào Adapter --> truyền vào AutoCompleteView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_item,list);
        autoPermisson.setAdapter(adapter);
    }

    private void AddEvents() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = edUser.getText().toString().trim();
                String pass = edPass.getText().toString().trim();
                String confirm = edConfirm.getText().toString().trim();
                String permisson = autoPermisson.getText().toString().trim();

                //Kiểm tra trống hoặc không trùng mật khẩu
                if (!pass.equals(confirm)) {
                    Toast.makeText(getContext(), R.string.donotmacth, Toast.LENGTH_LONG).show();
                } else if (user == "" || pass == "" || permisson=="") {
                    Toast.makeText(getContext(), R.string.donotempty, Toast.LENGTH_LONG).show();
                } else {
                    try {
                        // đưa dữ liệu từ editText vào Account
                        Account account = new Account(0, user, pass, permisson);

                        // tiến hành đăng ký tài khoản
                        ManagerSignUpAccount manaSignUpAccount = new ManagerSignUpAccount();
                        if (manaSignUpAccount.Insert(account) == true) {
                            Toast.makeText(getContext(), R.string.signupsuccess, Toast.LENGTH_LONG).show();
                            ClearEditText();
                        } else {
                            Toast.makeText(getContext(), R.string.signfail, Toast.LENGTH_LONG).show();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manager_sign_up, container, false);
        btnSignUp = (Button) view.findViewById(R.id.btnLogin);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        edUser = (EditText) view.findViewById(R.id.edUsername);
        edPass = (EditText) view.findViewById(R.id.edPassword);
        edConfirm = (EditText) view.findViewById(R.id.edConfirmPassword);
        autoPermisson = (AutoCompleteTextView) view.findViewById(R.id.autoPermission);

        AutoComplete();

        AddEvents();
        return view;
    }

}
