package com.example.phoneshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.phoneshop.R;
import com.example.phoneshop.activity.ChiTietSPActivity;
import com.example.phoneshop.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> {

    Context context;
    ArrayList<SanPham> sanPhams;

    public SanPhamAdapter(Context context, ArrayList<SanPham> sanPhams) {
        this.context = context;
        this.sanPhams = sanPhams;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_spmoinhat, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SanPham sanPham = sanPhams.get(position);
        holder.textViewTen.setText(sanPham.getTenSP());
//        holder.textViewGia.setText(sanPham.getGiaBan());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.textViewGia.setText("Giá: "+ decimalFormat.format(Integer.parseInt(sanPham.getGiaBan()))+" Đ");
//        Picasso.get().load(sanPham.getHinhAnh()).into(holder.imageView);
        Picasso.get().load(sanPham.getHinhAnh())
                .placeholder(R.drawable.iconloiimage)
                .error(R.drawable.iconloiimage)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return sanPhams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewTen, textViewGia;
        public ViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewSP);
            textViewTen= itemView.findViewById(R.id.textViewTenSP);
            textViewGia = itemView.findViewById(R.id.textViewGiaSP);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChiTietSPActivity.class);
                    intent.putExtra("sanpham",sanPhams.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }

}
