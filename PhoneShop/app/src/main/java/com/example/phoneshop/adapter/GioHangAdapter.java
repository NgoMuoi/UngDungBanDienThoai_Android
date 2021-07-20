package com.example.phoneshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phoneshop.R;
import com.example.phoneshop.activity.GioHangActivity;
import com.example.phoneshop.activity.MainActivity;
import com.example.phoneshop.model.GioHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class  GioHangAdapter extends BaseAdapter {
    Context  context;
    ArrayList<GioHang> gioHangs;
    public GioHangAdapter(Context context, ArrayList<GioHang> gioHangs) {
        this.context = context;
        this.gioHangs = gioHangs;
    }

    @Override
    public int getCount() {
        return gioHangs.size();
    }

    @Override
    public Object getItem(int i) {
        return gioHangs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView ten,gia;
        public ImageView hinh;
        public Button tru,values,cong;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(viewHolder == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_giohang,null);
            viewHolder.ten = (TextView) view.findViewById(R.id.txtTenSP);
            viewHolder.hinh = (ImageView) view.findViewById(R.id.imgGiohang);
            viewHolder.gia = (TextView) view.findViewById(R.id.txtGia);
            viewHolder.cong = (Button) view.findViewById(R.id.btnCong);
            viewHolder.values = (Button) view.findViewById(R.id.btnValues);
            viewHolder.tru = (Button) view.findViewById(R.id.btnTru);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        GioHang gioHang =(GioHang) getItem(i);
        viewHolder.ten.setText(gioHang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.gia.setText("Giá: "+ decimalFormat.format(gioHang.getGia())+" Đ");
        Picasso.get().load(gioHang.getHinhanh())
                .placeholder(R.drawable.iconloiimage)
                .error(R.drawable.iconloiimage)
                .into(viewHolder.hinh);

        viewHolder.values.setText(gioHang.getSoluong()+"");
        int sl = Integer.parseInt(viewHolder.values.getText().toString());
        if(sl >=10){
            viewHolder.cong.setVisibility(View.INVISIBLE);
            viewHolder.tru.setVisibility(View.VISIBLE);
        }else if(sl <=1) {
            viewHolder.tru.setVisibility(View.INVISIBLE);
        }else if(sl>=1){
            viewHolder.cong.setVisibility(View.VISIBLE);
            viewHolder.tru.setVisibility(View.VISIBLE);
        }
        ViewHolder finalViewHolder = viewHolder;
        viewHolder.cong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoi = Integer.parseInt(finalViewHolder.values.getText().toString()) + 1;
                int slht = MainActivity.mangGiohang.get(i).getSoluong();
                long giaht= MainActivity.mangGiohang.get(i).getGia();
                MainActivity.mangGiohang.get(i).setSoluong(slmoi);
                long giamoi = (giaht * slmoi) / slht;
                MainActivity.mangGiohang.get(i).setGia(giamoi);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.gia.setText("Giá: "+ decimalFormat.format(giamoi)+" Đ");


                com.example.phoneshop.activity.GioHangActivity.EventUltil();
                if(slmoi > 9){
                    finalViewHolder.cong.setVisibility(View.INVISIBLE);
                    finalViewHolder.tru.setVisibility(View.VISIBLE);
                    finalViewHolder.values.setText(slmoi+"");
                }else {
                    finalViewHolder.cong.setVisibility(View.VISIBLE);
                    finalViewHolder.tru.setVisibility(View.VISIBLE);
                    finalViewHolder.values.setText(slmoi+"");
                }
            }
        });
        viewHolder.tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoi = Integer.parseInt(finalViewHolder.values.getText().toString()) - 1;
                int slht = MainActivity.mangGiohang.get(i).getSoluong();
                long giaht= MainActivity.mangGiohang.get(i).getGia();
                MainActivity.mangGiohang.get(i).setSoluong(slmoi);
                long giamoi = (giaht * slmoi) / slht;
                MainActivity.mangGiohang.get(i).setGia(giamoi);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.gia.setText("Giá: "+ decimalFormat.format(giamoi)+" Đ");


                com.example.phoneshop.activity.GioHangActivity.EventUltil();
                if(slmoi < 2){
                    finalViewHolder.cong.setVisibility(View.VISIBLE);
                    finalViewHolder.tru.setVisibility(View.INVISIBLE);
                    finalViewHolder.values.setText(slmoi+"");
                }else {
                    finalViewHolder.cong.setVisibility(View.VISIBLE);
                    finalViewHolder.tru.setVisibility(View.VISIBLE);
                    finalViewHolder.values.setText(slmoi+"");
                }
            }
        });
        return view;
    }


}
