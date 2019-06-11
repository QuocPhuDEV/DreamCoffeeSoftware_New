package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Voucher;

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

public class VoucherAdapter extends BaseAdapter {


    private Context mContext;
    private int layout;
    private List<Voucher> voucherList;

    public VoucherAdapter(Context mContext, int layout, List<Voucher> voucherList) {
        this.mContext = mContext;
        this.layout = layout;
        this.voucherList = voucherList;
    }

    @Override
    public int getCount() {
        return voucherList.size();
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
        public TextView tvVoucher, tvVoucherContent;
        public ImageView imgVoucher;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VoucherAdapter.ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder = new VoucherAdapter.ViewHolder();
            viewHolder.tvVoucher = (TextView) convertView.findViewById(R.id.tvVoucher);
            viewHolder.tvVoucherContent = (TextView) convertView.findViewById(R.id.tvVoucherContent);
            viewHolder.imgVoucher = (ImageView) convertView.findViewById(R.id.imgVoucher);

            convertView.setTag(viewHolder);
            convertView.setTag(R.id.tvVoucher, viewHolder.tvVoucher);
            convertView.setTag(R.id.tvVoucherContent, viewHolder.tvVoucherContent);
            convertView.setTag(R.id.imgVoucher, viewHolder.imgVoucher);

        } else {
            viewHolder = (VoucherAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.tvVoucher.setText("Mã Voucher: " + voucherList.get(position).getVoucher());
        String contentSale1 = "Áp dụng voucher sẽ được giảm ";
        String contentSale2 = "% đối với đơn hàng trên 50,000 vnđ";
        viewHolder.tvVoucherContent.setText(contentSale1 + String.valueOf(voucherList.get(position).getNumberPercent()) + contentSale2);
        viewHolder.imgVoucher.setImageBitmap(getBitmapFromAssets("voucher/H" + voucherList.get(position).getID_Voucher() + ".jpg"));
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
