package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Accounts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Account;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.AccountModel;
import com.example.phuhandsome.dreamcoffeesoftware.HomeFragment;
import com.example.phuhandsome.dreamcoffeesoftware.MainActivity;
import com.example.phuhandsome.dreamcoffeesoftware.R;

import java.sql.SQLException;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Button btnLogin, btnCancel;
    EditText edUser, edPass;
    CheckBox ckbRemember;
    public static String userNameLogin;
    public static String idAccountLogin="";
    public static String permissionLogin="";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    private void AddEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edUser.getText().toString().trim();
                String pass = edPass.getText().toString().trim();

                // Kiểm tra rỗng
                if (user == "" || pass == "") {
                    Toast.makeText(getContext(), R.string.donotempty, Toast.LENGTH_LONG).show();
                } else {
                    // nếu có dữ liệu thì kiểm tra với databases
                    try {
                        AccountModel accountModel = new AccountModel();
                        List<Account> listAccount;
                        listAccount = accountModel.getAccountList();

                        // tạo biến kiểm tra xem có tồn tại tài khoản nào người dùng nhập vào hay không
                        int flag = 0;
                        // duyệt mảng dữ liệu trong bảng Account
                        for (Account account : listAccount) {
                            if (user.equals(account.getUserName()) && pass.equals(account.getPassWord())) {
                                flag = 1;
                                permissionLogin += account.getPermission();
                                idAccountLogin +=account.getId_Account();
                                break;
                            } else {
                                flag = 0;
                            }
                        }
                        // nếu có tồn tại tài khoản hoặc không.
                        if (flag == 1) {
                            userNameLogin = user;
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(getContext(), R.string.loginsuccess, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getContext(), R.string.loginfail, Toast.LENGTH_LONG).show();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        btnLogin = (Button) view.findViewById(R.id.btnLogin);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        edUser = (EditText) view.findViewById(R.id.edUsername);
        edPass = (EditText) view.findViewById(R.id.edPassword);

        AddEvent();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
