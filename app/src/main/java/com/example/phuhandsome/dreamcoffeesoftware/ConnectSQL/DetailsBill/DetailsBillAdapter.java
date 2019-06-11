package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.DetailsBill;

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

public class DetailsBillAdapter extends BaseAdapter {

    private Context mContext;
    private int layout;
    private List<DetailsBill> detailsBillsList;

    public DetailsBillAdapter(Context mContext, int layout, List<DetailsBill> detailsBillsList) {
        this.mContext = mContext;
        this.layout = layout;
        this.detailsBillsList = detailsBillsList;
    }

    @Override
    public int getCount() {
        return detailsBillsList.size();
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
        public TextView tvDrinkName, tvNumber;
        public ImageView imgStatus;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetailsBillAdapter.ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder = new DetailsBillAdapter.ViewHolder();

            viewHolder.tvDrinkName = (TextView) convertView.findViewById(R.id.tvDrinks);
            viewHolder.tvNumber = (TextView) convertView.findViewById(R.id.tvNumber);
            viewHolder.imgStatus = (ImageView) convertView.findViewById(R.id.imgStatus);

            convertView.setTag(viewHolder);
            convertView.setTag(R.id.tvDrinks, viewHolder.tvDrinkName);
            convertView.setTag(R.id.tvNumber, viewHolder.tvNumber);
            convertView.setTag(R.id.imgStatus, viewHolder.imgStatus);

        } else {
            viewHolder = (DetailsBillAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.tvDrinkName.setText(detailsBillsList.get(position).getDrinksName());
        viewHolder.tvNumber.setText(String.valueOf(detailsBillsList.get(position).getNumberOrder()));
        /*viewHolder.imgDrink.setImageBitmap(getBitmapFromAssets("menuimages/H"+menuDrinkList.get(position).getID_Drink() + ".jpg"));*/
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.imgStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalViewHolder.imgStatus.setImageResource(R.drawable.check);
            }
        });
        return convertView;
    }
    /*public Bitmap getBitmapFromAssets(String fileName) {
        AssetManager assetManager = mContext.getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }*/
}