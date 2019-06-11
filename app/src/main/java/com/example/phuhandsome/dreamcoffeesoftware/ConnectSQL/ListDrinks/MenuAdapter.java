package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.ListDrinks;

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

import com.example.phuhandsome.dreamcoffeesoftware.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MenuAdapter extends BaseAdapter {

    private Context mContext;
    private int layout;
    private List<Menu> menuDrinkList;

    public MenuAdapter(Context mContext, int layout, List<Menu> menuDrinkList) {
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
        public TextView tvDrinkName, tvPrice,tvStatus;
        public ImageView imgDrink;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder = new ViewHolder();
            viewHolder.tvDrinkName = (TextView) convertView.findViewById(R.id.tvDrinkName);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
            viewHolder.tvStatus = (TextView) convertView.findViewById(R.id.tvStatus);
            viewHolder.imgDrink = (ImageView) convertView.findViewById(R.id.imgDrink);

            convertView.setTag(viewHolder);
            convertView.setTag(R.id.tvDrinkName, viewHolder.tvDrinkName);
            convertView.setTag(R.id.tvPrice, viewHolder.tvPrice);
            convertView.setTag(R.id.tvStatus, viewHolder.tvStatus);
            convertView.setTag(R.id.imgDrink, viewHolder.imgDrink);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvDrinkName.setText(menuDrinkList.get(position).getDrinks());
        viewHolder.tvPrice.setText("Giá: "+ String.valueOf(menuDrinkList.get(position).getPrice()) + " vnđ");
        String status="";
        if(menuDrinkList.get(position).getInventory() >0){
            status ="còn hàng";
        }else {
            status ="hết hàng";
        }
        viewHolder.tvStatus.setText("Tình trạng: "+status);
        viewHolder.imgDrink.setImageBitmap(getBitmapFromAssets("menuimages/H"+menuDrinkList.get(position).getID_Drink() + ".jpg"));
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
