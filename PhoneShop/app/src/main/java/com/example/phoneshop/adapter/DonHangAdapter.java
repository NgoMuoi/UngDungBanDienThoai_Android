package com.example.phoneshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.phoneshop.R;
import com.example.phoneshop.activity.ChiTietDonHangActivity;
import com.example.phoneshop.model.DonHang;
import java.util.ArrayList;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder>{
    Context context;
    ArrayList<DonHang> donHangs;

    public DonHangAdapter(Context context, ArrayList<DonHang> donHangs) {
        this.context = context;
        this.donHangs = donHangs;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_donhang, parent,false);
        return new DonHangAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DonHang donHang = donHangs.get(position);
        holder.textViewMaDH.setText("Mã đơn: " + donHang.getId());
        holder.textViewTen.setText(donHang.getNguoiNhan());
        holder.textViewSDT.setText("ĐT: "+donHang.getSDTgiaohang());
        holder.textViewDiaChi.setText("ĐC: "+donHang.getDiaChigiaohang());
        holder.textViewNgayDat.setText(donHang.getNgayDat());
        holder.textViewNgayGiao.setText("-  "+donHang.getNgayGiao());
        holder.textViewTinhTrang.setText("Tình trạng: "+donHang.getTinhTrang());
    }

    @Override
    public int getItemCount() {
        return donHangs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMaDH, textViewTen, textViewSDT, textViewDiaChi, textViewNgayDat, textViewNgayGiao, textViewTinhTrang;
        public ViewHolder(View itemView){
            super(itemView);
            textViewMaDH = itemView.findViewById(R.id.txtMaDH);
            textViewTen= itemView.findViewById(R.id.txtNguoiNhan);
            textViewSDT = itemView.findViewById(R.id.txtSDT);
            textViewDiaChi = itemView.findViewById(R.id.txtDiaChi);
            textViewNgayDat = itemView.findViewById(R.id.txtNgayDat);
            textViewNgayGiao = itemView.findViewById(R.id.txtNgayGiao);
            textViewTinhTrang = itemView.findViewById(R.id.txtTinhTrang);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChiTietDonHangActivity.class);
                    intent.putExtra("donhang", donHangs.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
