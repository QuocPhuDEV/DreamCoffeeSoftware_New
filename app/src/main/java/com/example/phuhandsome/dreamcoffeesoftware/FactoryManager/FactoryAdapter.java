package com.example.phuhandsome.dreamcoffeesoftware.FactoryManager;

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

public class FactoryAdapter extends BaseAdapter {


    private Context mContext;
    private int layout;
    private List<Menu> menuDrinkList;

    public FactoryAdapter(Context mContext, int layout, List<Menu> menuDrinkList) {
        this.mContext = mContext;
        this.layout = layout;
        this.menuDrinkList = menuDrinkList;
    }


    @Override
    public int getCount() {
        return menuDrinkList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder{
        public TextView tvDrinks, tvPrices, tvNumber;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FactoryAdapter.ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            viewHolder = new FactoryAdapter.ViewHolder();
            viewHolder.tvDrinks = (TextView) convertView.findViewById(R.id.tvDrinks);
            viewHolder.tvPrices = (TextView) convertView.findViewById(R.id.tvPrices);
            viewHolder.tvNumber = (TextView) convertView.findViewById(R.id.tvNumber);

            convertView.setTag(viewHolder);
            convertView.setTag(R.id.tvDrinks, viewHolder.tvDrinks);
            convertView.setTag(R.id.tvPrices, viewHolder.tvPrices);
            convertView.setTag(R.id.tvNumber, viewHolder.tvNumber);

        } else {
            viewHolder = (FactoryAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.tvDrinks.setText(menuDrinkList.get(position).getDrinks());
        viewHolder.tvPrices.setText("Giá: "+ String.valueOf(menuDrinkList.get(position).getPrice()) + " vnđ");
        viewHolder.tvNumber.setText("Tồn kho: "+String.valueOf(menuDrinkList.get(position).getInventory()));
        return convertView;
    }
}
