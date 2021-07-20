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
import com.example.phoneshop.model.LoaiSP;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LoaiSPAdapter extends ArrayAdapter<LoaiSP> {
    public LoaiSPAdapter(@NonNull Context context, int resource, @NonNull List<LoaiSP> objects) {
        super(context, resource, objects);
    }

    class ViewHolder{
        TextView textView;
        ImageView imageView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder = null;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.layout_gridloaisp, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.txtTitle);
            viewHolder.imageView= convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        LoaiSP loaiSP = getItem(position);
        Picasso.get().load(loaiSP.getHinhAnh()).into(viewHolder.imageView);
        viewHolder.textView.setText(loaiSP.getTenLoai());
        return convertView;
    }
}
