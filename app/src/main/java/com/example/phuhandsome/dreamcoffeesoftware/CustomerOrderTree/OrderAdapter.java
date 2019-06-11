package com.example.phuhandsome.dreamcoffeesoftware.CustomerOrderTree;

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
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks.MenuFragment;
import com.example.phuhandsome.dreamcoffeesoftware.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends BaseAdapter {


    private Context mContext;
    private int layout;
    private List<Menu> menuDrinkList;

    public OrderAdapter(Context mContext, int layout, List<Menu> menuDrinkList) {
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

    static class ViewHolder {
        public TextView tvDrinkName, tvPrice, tvNumber;
        public ImageView imgDrink;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderAdapter.ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder = new OrderAdapter.ViewHolder();
            viewHolder.tvDrinkName = (TextView) convertView.findViewById(R.id.tvDrinkName);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
            viewHolder.tvNumber = (TextView) convertView.findViewById(R.id.tvnumber);
            viewHolder.imgDrink = (ImageView) convertView.findViewById(R.id.imgDrink);

            convertView.setTag(viewHolder);
            convertView.setTag(R.id.tvDrinkName, viewHolder.tvDrinkName);
            convertView.setTag(R.id.tvPrice, viewHolder.tvPrice);
            convertView.setTag(R.id.tvnumber, viewHolder.tvNumber);
            convertView.setTag(R.id.imgDrink, viewHolder.imgDrink);

        } else {
            viewHolder = (OrderAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.tvDrinkName.setText(menuDrinkList.get(position).getDrinks());
        viewHolder.tvPrice.setText("Giá: " + String.valueOf(menuDrinkList.get(position).getPrice()) + " vnđ");

        OrderFragment orderFragment = new OrderFragment();
        int order = orderFragment.orderNumber;
        viewHolder.tvNumber.setText("Số lượng: " + order);
        viewHolder.imgDrink.setImageBitmap(getBitmapFromAssets("menuimages/H" + menuDrinkList.get(position).getID_Drink() + ".jpg"));
        return convertView;
    }

    public Bitmap getBitmapFromAssets(String fileName) {
        AssetManager assetManager = mContext.getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }
}
