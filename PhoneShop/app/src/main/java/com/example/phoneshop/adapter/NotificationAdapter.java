package com.example.phoneshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phoneshop.R;
import com.example.phoneshop.model.GioHang;
import com.example.phoneshop.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class NotificationAdapter extends BaseAdapter {
    Context context;
    ArrayList<SanPham> sanPhams;
    public NotificationAdapter(Context context, ArrayList<SanPham> sanPhams) {
        this.context = context;
        this.sanPhams = sanPhams;
    }
    @Override
    public int getCount() {
        return sanPhams.size();
    }

    @Override
    public Object getItem(int i) {
        return sanPhams.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView ten,gia;
        public ImageView hinh;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        NotificationAdapter.ViewHolder viewHolder = null;
        if(viewHolder == null){
            viewHolder = new NotificationAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_notification,null);
            viewHolder.ten = (TextView) view.findViewById(R.id.txtTenSP);
            viewHolder.hinh = (ImageView) view.findViewById(R.id.imgGiohang);
            viewHolder.gia = (TextView) view.findViewById(R.id.txtGia);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (NotificationAdapter.ViewHolder) view.getTag();
        }
        SanPham sanPham =(SanPham) getItem(i);
        viewHolder.ten.setText(sanPham.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.gia.setText("Giá: "+ decimalFormat.format(Integer.parseInt(sanPham.getGiaBan()))+" Đ");
        Picasso.get().load(sanPham.getHinhAnh())
                .placeholder(R.drawable.iconloiimage)
                .error(R.drawable.iconloiimage)
                .into(viewHolder.hinh);

        return view;
    }

}
