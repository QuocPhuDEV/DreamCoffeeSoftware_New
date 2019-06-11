package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Sale;

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

public class SaleAdapter extends BaseAdapter {


    private Context mContext;
    private int layout;
    private List<Sale> saleList;

    public SaleAdapter(Context mContext, int layout, List<Sale> saleList) {
        this.mContext = mContext;
        this.layout = layout;
        this.saleList = saleList;
    }

    @Override
    public int getCount() {
        return saleList.size();
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
        public TextView tvDateSale, tvSaleContent;
        public ImageView imgSale;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SaleAdapter.ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder = new SaleAdapter.ViewHolder();
            viewHolder.tvDateSale = (TextView) convertView.findViewById(R.id.tvDateSale);
            viewHolder.tvSaleContent = (TextView) convertView.findViewById(R.id.tvSaleContent);
            viewHolder.imgSale = (ImageView) convertView.findViewById(R.id.imgSale);

            convertView.setTag(viewHolder);
            convertView.setTag(R.id.tvDateSale, viewHolder.tvDateSale);
            convertView.setTag(R.id.tvSaleContent, viewHolder.tvSaleContent);
            convertView.setTag(R.id.imgSale, viewHolder.imgSale);

        } else {
            viewHolder = (SaleAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.tvDateSale.setText("Ngày khuyến mãi: " + saleList.get(position).getDatesale());
        String contentSale1 = "Tưng bừng khuyến mãi, giảm giá ngay ";
        String contentSale2 = "% đối với tất cả đơn hàng trên 100,000 vnđ";
        viewHolder.tvSaleContent.setText(contentSale1 + String.valueOf(saleList.get(position).getPercentSale()) + contentSale2);
        viewHolder.imgSale.setImageBitmap(getBitmapFromAssets("sale/H" + saleList.get(position).getID_Sale() + ".jpg"));
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
