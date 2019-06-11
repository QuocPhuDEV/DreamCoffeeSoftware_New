package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Employee;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.Menu;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.MenuAdapter;
import com.example.phuhandsome.dreamcoffeesoftware.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class EmployeeAdapter extends BaseAdapter {


    private Context mContext;
    private int layout;
    private List<Empoyee> empoyeeList;

    public EmployeeAdapter(Context mContext, int layout, List<Empoyee> empoyeeList) {
        this.mContext = mContext;
        this.layout = layout;
        this.empoyeeList = empoyeeList;
    }

    @Override
    public int getCount() {
        return empoyeeList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
        public TextView tvEmployeeName, tvSex, tvEmployeePhone;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EmployeeAdapter.ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            viewHolder = new EmployeeAdapter.ViewHolder();
            viewHolder.tvEmployeeName = (TextView) convertView.findViewById(R.id.tvEmployeeName);
            viewHolder.tvSex = (TextView) convertView.findViewById(R.id.tvSex);
            viewHolder.tvEmployeePhone = (TextView) convertView.findViewById(R.id.tvEmployeePhone);

            convertView.setTag(viewHolder);
            convertView.setTag(R.id.tvEmployeeName, viewHolder.tvEmployeeName);
            convertView.setTag(R.id.tvSex, viewHolder.tvSex);
            convertView.setTag(R.id.tvEmployeePhone, viewHolder.tvEmployeePhone);

        } else {
            viewHolder = (EmployeeAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.tvEmployeeName.setText(empoyeeList.get(position).getEmployeename());
        viewHolder.tvSex.setText("Giới tính: " + empoyeeList.get(position).getSex());
        viewHolder.tvEmployeePhone.setText("SĐT: " + empoyeeList.get(position).getPhonenumber());
        return convertView;
    }

}
