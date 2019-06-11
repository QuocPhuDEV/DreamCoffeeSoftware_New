package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Accounts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Account;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.SignUpAccount;
import com.example.phuhandsome.dreamcoffeesoftware.MainActivity;
import com.example.phuhandsome.dreamcoffeesoftware.R;

import java.sql.SQLException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SignUpFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Button btnSignUp, btnCancel;
    EditText edUser, edPass, edConfirm;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
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

    // Clear editText
    private void ClearEditText() {
        edUser.setText(null);
        edPass.setText(null);
        edConfirm.setText(null);
    }

    // Thêm sự kiện
    private void AddEvents() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edUser.getText().toString().trim();
                String pass = edPass.getText().toString().trim();
                String confirm = edConfirm.getText().toString().trim();

                //Kiểm tra trống hoặc không trùng mật khẩu
                if (!pass.equals(confirm)) {
                    Toast.makeText(getContext(), R.string.donotmacth, Toast.LENGTH_LONG).show();
                } else if (user == "" || pass == "") {
                    Toast.makeText(getContext(), R.string.donotempty, Toast.LENGTH_LONG).show();
                } else {
                    try {
                        // đưa dữ liệu từ editText vào Account
                        Account account = new Account(0, user, pass, null);

                        // tiến hành đăng ký tài khoản
                        SignUpAccount signUpAccount = new SignUpAccount();
                        if (signUpAccount.Insert(account) == true) {
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
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        btnSignUp = (Button) view.findViewById(R.id.btnLogin);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        edUser = (EditText) view.findViewById(R.id.edUsername);
        edPass = (EditText) view.findViewById(R.id.edPassword);
        edConfirm = (EditText) view.findViewById(R.id.edConfirmPassword);

        AddEvents();
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
