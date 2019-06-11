package com.example.phuhandsome.dreamcoffeesoftware.CustomerOrderTree;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.Menu;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.MenuFragment;
import com.example.phuhandsome.dreamcoffeesoftware.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BillAdapter extends BaseAdapter {


    private Context mContext;
    private int layout;
    private List<Menu> menuDrinkListSelected;

    public BillAdapter(Context mContext, int layout, List<Menu> menuDrinkList) {
        this.mContext = mContext;
        this.layout = layout;
        this.menuDrinkListSelected = menuDrinkList;
    }

    @Override
    public int getCount() {
        return menuDrinkListSelected.size();
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
        public TextView tvDrinks, tvPrice, tvNumberOrder, tvTotal;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BillAdapter.ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            viewHolder = new BillAdapter.ViewHolder();
            viewHolder.tvDrinks = (TextView) convertView.findViewById(R.id.tvDrinks);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
            viewHolder.tvNumberOrder = (TextView) convertView.findViewById(R.id.tvNumberOrder);
            viewHolder.tvTotal = (TextView) convertView.findViewById(R.id.tvTotal);

            convertView.setTag(viewHolder);
            convertView.setTag(R.id.tvDrinks, viewHolder.tvDrinks);
            convertView.setTag(R.id.tvPrice, viewHolder.tvPrice);
            convertView.setTag(R.id.tvNumberOrder, viewHolder.tvNumberOrder);
            convertView.setTag(R.id.tvTotal, viewHolder.tvTotal);

        } else {
            viewHolder = (BillAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.tvDrinks.setText(menuDrinkListSelected.get(position).getDrinks());
        viewHolder.tvPrice.setText(String.valueOf(menuDrinkListSelected.get(position).getPrice()));
        MenuFragment menuFragment = new MenuFragment();
        int numberOrder = menuFragment.orderNumber;
        viewHolder.tvNumberOrder.setText(String.valueOf(numberOrder));
        int total = menuDrinkListSelected.get(position).getPrice() * numberOrder;
        viewHolder.tvTotal.setText(String.valueOf(total));

        return convertView;
    }

}