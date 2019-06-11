package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.DetailsBill;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.Menu;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.MenuAdapter;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.MenuList;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Request.Request;
import com.example.phuhandsome.dreamcoffeesoftware.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailsBillFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailsBillFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsBillFragment extends Fragment {
    private GridView gvOrder;
    private DetailsBillAdapter adapter;
    private List<DetailsBill> detailsBillList;
    private ImageView imgStatus;

    private Button btnRefresh;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DetailsBillFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DetailsBillFragment newInstance(String param1, String param2) {
        DetailsBillFragment fragment = new DetailsBillFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            View view = inflater.inflate(R.layout.fragment_details_bill, container, false);
            gvOrder = view.findViewById(R.id.gvOrder);
            btnRefresh = (Button) view.findViewById(R.id.btnRefresh);
            imgStatus = (ImageView) view.findViewById(R.id.imgStatus);
            btnRefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddEvent();
                    AddList();
                    CheckRequest();
                    Toast.makeText(getContext(), "Danh sách đang trong trạng thái mới nhất!", Toast.LENGTH_SHORT).show();
                }
            });

            AddEvent();
            AddList();
            ClickPay();
            // Inflate the layout for this fragment
            return view;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // thêm sự kiện
    private void AddEvent() {
        try {
            detailsBillList = new ArrayList<>();
            adapter = new DetailsBillAdapter(getContext(), R.layout.detailsbill_order_items, detailsBillList);
            gvOrder.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // thêm dữ liệu vào chuỗi
    private void AddList() {
        try {
            // gọi class truy vấn sql
            DetailsBillList list = new DetailsBillList();
            //tạo 1 list mới chứa kết quả truy vấn từ sql
            List<DetailsBill> newList;
            newList = list.getBill();
            // duyệt mảng list vừa có để lấy kết quả truyền vào menuDrinkList để truyền vào adapter
            for (DetailsBill bill : newList) {
                detailsBillList.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
        }

    }

    // sự kiện click vào Imageview thanh toán
    private void ClickPay() {
        imgStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Xác nhận đã thu tiền và xóa yêu cầu");
                builder.setCancelable(false);
                builder.setPositiveButton("Không xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setNegativeButton("Đã thu tiền", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DeleteDataDetailsBill();
                        DeleteDataRequest();
                        Toast.makeText(getContext(), "Đã xóa thành công, vùng lòng Refresh ", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });
    }

    // Xóa dữ liệu bảng order sau khi đã thanh toán xong.
    private void DeleteDataDetailsBill() {
        try {
            int table = 1;

            DetailsBillDelete detailsBillDelete = new DetailsBillDelete();
            DetailsBill detailsBill = new DetailsBill(table, null, 0);

            if (detailsBillDelete.Delete(detailsBill) == true) {
                // Toast.makeText(this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
            } else if (detailsBillDelete.Delete(detailsBill) == false) {
                //Toast.makeText(this, "Xóa không thành công!, vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

    }

    // Xóa dữ liệu bảng order sau khi đã thanh toán xong.
    private void DeleteDataRequest() {
        try {
            int table = 1;

            DetailsBillDelete detailsBillDelete = new DetailsBillDelete();
            Request request = new Request(1, null);

            if (detailsBillDelete.DeleteRequest(request) == true) {
                // Toast.makeText(this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
            } else if (detailsBillDelete.DeleteRequest(request) == false) {
                //Toast.makeText(this, "Xóa không thành công!, vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

    }

    // check request thanh toán từ khách hàng
    private void CheckRequest() {
        try {
            DetailsBillList list = new DetailsBillList();
            List<Request> newList = list.getRequest();
            String requestCheck = "";
            int numtable = 0;
            for (Request request : newList) {
                numtable = Integer.valueOf(request.getNumtable());
                requestCheck = request.getRequest();
            }
            if (numtable == 1 && requestCheck.equals("true")) {
                imgStatus.setImageResource(R.drawable.check);
            } else {
                imgStatus.setImageResource(R.drawable.uncheck);
            }
        } catch (SQLException e) {

        }

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
