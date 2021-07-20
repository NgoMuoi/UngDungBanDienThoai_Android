package com.example.phoneshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.phoneshop.R;
import com.example.phoneshop.model.SanPham;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChiTietAdaper extends ArrayAdapter<SanPham> {
    public ChiTietAdaper(@NonNull Context context, int resource, @NonNull List<SanPham> objects) {
        super(context, resource, objects);
    }
    class ViewHolder{
        TextView textViewGia,textViewCauHinh;
        ImageView imageView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_chi_tiet_s_p, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView= convertView.findViewById(R.id.imgHinhAnh);
            viewHolder.textViewGia = convertView.findViewById(R.id.txtGia);
            viewHolder.textViewCauHinh= convertView.findViewById(R.id.txtCauHinh);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SanPham sanPham = getItem(position);
        Picasso.get().load(sanPham.getHinhAnh()).into(viewHolder.imageView);
        viewHolder.textViewGia.setText(sanPham.getGiaBan());
        viewHolder.textViewCauHinh.setText(sanPham.getMoTa());
        return convertView;
    }
}
