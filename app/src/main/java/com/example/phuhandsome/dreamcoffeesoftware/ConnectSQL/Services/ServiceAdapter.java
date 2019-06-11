package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Services;

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

import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Sale.Sale;
import com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Sale.SaleAdapter;
import com.example.phuhandsome.dreamcoffeesoftware.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ServiceAdapter extends BaseAdapter {


    private Context mContext;
    private int layout;
    private List<Services> servicesList;

    public ServiceAdapter(Context mContext, int layout, List<Services> servicesList) {
        this.mContext = mContext;
        this.layout = layout;
        this.servicesList = servicesList;
    }

    @Override
    public int getCount() {
        return servicesList.size();
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
        public TextView tvServices, tvServiceContent;
        public ImageView imgServices;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ServiceAdapter.ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder = new ServiceAdapter.ViewHolder();
            viewHolder.tvServices = (TextView) convertView.findViewById(R.id.tvServices);
            viewHolder.tvServiceContent = (TextView) convertView.findViewById(R.id.tvServiceContent);
            viewHolder.imgServices = (ImageView) convertView.findViewById(R.id.imgServices);

            convertView.setTag(viewHolder);
            convertView.setTag(R.id.tvServices, viewHolder.tvServices);
            convertView.setTag(R.id.tvSaleContent, viewHolder.tvServiceContent);
            convertView.setTag(R.id.imgServices, viewHolder.imgServices);

        } else {
            viewHolder = (ServiceAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.tvServices.setText("Dịch vụ: " + servicesList.get(position).getServicename());
        String contenSercives = String.valueOf(servicesList.get(position).getDescribe());
        viewHolder.tvServiceContent.setText(contenSercives);
        viewHolder.imgServices.setImageBitmap(getBitmapFromAssets("services/H" + servicesList.get(position).getID_Services() + ".jpg"));
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
