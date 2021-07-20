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
import com.example.phoneshop.activity.ChiTietDonHangActivity;
import com.example.phoneshop.activity.ChiTietSPActivity;
import com.example.phoneshop.model.CTDonHang;
import com.squareup.picasso.Picasso;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class CTDonHangAdapter extends RecyclerView.Adapter<CTDonHangAdapter.ViewHolder> {
    Context context;
    ArrayList<CTDonHang> ctDonHangs;

    public CTDonHangAdapter(Context context, ArrayList<CTDonHang> ctDonHangs) {
        this.context = context;
        this.ctDonHangs = ctDonHangs;
    }

    @Override
    public CTDonHangAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_ctdonhang, parent,false);

        return new CTDonHangAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CTDonHangAdapter.ViewHolder holder, int position) {
        CTDonHang ctDonHang = ctDonHangs.get(position);
        holder.textViewTen.setText(ctDonHang.getTenSP());
        holder.textViewSL.setText("Số lượng: "+ctDonHang.getSoLuong());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.textViewGia.setText("Thanh toán: "+ decimalFormat.format(Integer.parseInt(ctDonHang.getDonGia()))+" Đ");
        Picasso.get().load(ctDonHang.getHinhAnh())
                .placeholder(R.drawable.iconloiimage)
                .error(R.drawable.iconloiimage)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return ctDonHangs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewTen, textViewSL, textViewGia;
        public ViewHolder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.imgCTDH);
            textViewTen= itemView.findViewById(R.id.txtTenSP);
            textViewSL = itemView.findViewById(R.id.txtSL);
            textViewGia = itemView.findViewById(R.id.txtGia);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChiTietSPActivity.class);
                    intent.putExtra("ctdh",ctDonHangs.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}