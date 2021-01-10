package com.hfad.uts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class orderListAdapter extends ArrayAdapter<Order> {

    private  static final String  TAG = "orderListAdapter";
    private Context mContext;
    int mResource;

    public orderListAdapter(Context context, int resource, ArrayList<Order> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String namaMenu = getItem(position).getName();
        int qty = getItem(position).getQty();
        int harga = getItem(position).getPrice();

        Order or = new Order(namaMenu, qty, harga);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView menuTxt = (TextView) convertView.findViewById(R.id.menu);
        TextView qtyTxt = (TextView) convertView.findViewById(R.id.qtyTxt);

        menuTxt.setText(namaMenu);
        qtyTxt.setText("Rp. " + harga +" x "+ qty);

        return convertView;
    }
}
