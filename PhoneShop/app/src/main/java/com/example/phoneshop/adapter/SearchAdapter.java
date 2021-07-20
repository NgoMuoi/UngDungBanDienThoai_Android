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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    Context context;
    ArrayList<SanPham> sanPhams;

    public SearchAdapter(Context context, ArrayList<SanPham> sanPhams) {
        this.context = context;
        this.sanPhams = sanPhams;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_search,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SanPham sanPham = sanPhams.get(position);
        holder.tensp.setText(sanPham.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.giasp.setText("Giá: "+ decimalFormat.format(Integer.parseInt(sanPham.getGiaBan()))+" Đ");
        Picasso.get().load(sanPham.getHinhAnh())
                .placeholder(R.drawable.iconloiimage)
                .error(R.drawable.iconloiimage)
                .into(holder.hinhanh);
    }

    @Override
    public int getItemCount() {
        return sanPhams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tensp,giasp;
        ImageView hinhanh;
        public ViewHolder(View itemView) {
            super(itemView);
            tensp = itemView.findViewById(R.id.txtTen);
            giasp = itemView.findViewById(R.id.txtGia);
            hinhanh = itemView.findViewById(R.id.imgSearch);
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
