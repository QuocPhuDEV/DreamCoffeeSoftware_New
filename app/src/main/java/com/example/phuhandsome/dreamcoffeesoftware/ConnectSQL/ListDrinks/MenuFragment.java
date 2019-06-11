package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Account;
import com.example.phuhandsome.dreamcoffeesoftware.MainActivity;
import com.example.phuhandsome.dreamcoffeesoftware.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements SearchView.OnQueryTextListener {
    private GridView gvMenuDrink;
    private MenuAdapter adapter;
    private List<Menu> menuDrinkList;

    private Dialog dialog;

    public static int orderNumber = 0;
    public static String drinksName;
    public static int id_DrinksName;

    private Button btnOrder, btnCancel;
    private EditText edorderNumber;

    public static ArrayList listMenuSelected = new ArrayList();
    public static ArrayList<Integer> listNumberOrder = new ArrayList();


    public MenuFragment() {
        // Required empty public constructor
    }

    private void AddEvent() {
        try {
            menuDrinkList = new ArrayList<>();
            adapter = new MenuAdapter(getContext(), R.layout.menu_items, menuDrinkList);
            gvMenuDrink.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // show dialog nhập số lượng khi gọi món
    /*public void showDialog() {
        try {
            dialog = new Dialog(getContext());
            dialog.setTitle("Xác nhận gọi đồ uống");
            dialog.setContentView(R.layout.order_dialog);


            btnOrder = (Button) dialog.findViewById(R.id.btnOrder);
            btnCancel = (Button) dialog.findViewById(R.id.btncancelorder);
            edorderNumber = (EditText) dialog.findViewById(R.id.orderNumber);

            // sau khi bấm vào nút gọi đồ
            btnOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (edorderNumber.getText().toString().equals("")) {
                        orderNumber = 1;
                        listMenuSelected.add(id_DrinksName);
                        listNumberOrder.add(orderNumber);
                        dialog.dismiss();
                        Toast.makeText(getContext(), "Đã order thành công: " + orderNumber + " " + drinksName, Toast.LENGTH_SHORT).show();
                    } else {

                        orderNumber = Integer.parseInt(edorderNumber.getText().toString().trim());
                        listMenuSelected.add(id_DrinksName);
                        listNumberOrder.add(orderNumber);
                        dialog.dismiss();
                        Toast.makeText(getContext(), "Đã order thành công: " + orderNumber + " " + drinksName, Toast.LENGTH_SHORT).show();

                    }
                }
            });
            // sau khi bấm vào nút cancel ko gọi món
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

/*    // loading
    public void LoadingDialog() {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("abvjdab");
        progressDialog.setMessage("loading");
        progressDialog.show();
    }*/

    // show dialog xác nhận khi gọi món
    public void showAlertDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xác nhận gọi đồ uống: " + drinksName);
        //builder.setMessage("");
        builder.setCancelable(false);
        builder.setPositiveButton("Không, cảm ơn!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setNegativeButton("Gọi ngay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                orderNumber = 1;
                listMenuSelected.add(id_DrinksName);
                Toast.makeText(getContext(), "Đã order thành công 1 " + drinksName, Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    // thêm dữ liệu vào chuỗi
    private void AddList() {

        try {
            // gọi class truy vấn sql
            MenuList menuList = new MenuList();
            //tạo 1 list mới chứa kết quả truy vấn từ sql
            List<Menu> list;
            list = menuList.getMenuList();
            // duyệt mảng list vừa có để lấy kết quả truyền vào menuDrinkList để truyền vào adapter
            for (Menu menu : list) {
                menuDrinkList.add(menu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        try {
            View view = inflater.inflate(R.layout.fragment_menu, container, false);
            gvMenuDrink = view.findViewById(R.id.gvMenuDrink);

            AddEvent();
            AddList();

            gvMenuDrink.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    drinksName = menuDrinkList.get(position).getDrinks();
                    id_DrinksName = menuDrinkList.get(position).getID_Drink();
                    showAlertDialog();
                    //showDialog();
                }
            });
            // Inflate the layout for this fragment
            return view;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Override lại để thực hiện chức năng Search
    @Override
    public void onCreateOptionsMenu(android.view.Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.itemSearch);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);
        setHasOptionsMenu(true);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(getContext(), "" + query, Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String drinks) {
        try {
            MenuList menuList = new MenuList();
            List<Menu> newList;
            newList = menuList.getSearchMenu(drinks);
            for (Menu menu : newList) {
                menuDrinkList.add(menu);
            }
            adapter = new MenuAdapter(getContext(), R.layout.menu_items, menuDrinkList);
            gvMenuDrink.setAdapter(adapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
