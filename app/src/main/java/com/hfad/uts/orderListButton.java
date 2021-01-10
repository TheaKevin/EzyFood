package com.hfad.uts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class orderListButton extends ArrayAdapter<Order> {

    private  static final String  TAG = "orderListAdapter";
    private Context mContext;
    int mResource;
    ArrayList<Order> ar;

    public orderListButton(Context context, int resource, ArrayList<Order> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        ar = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        String namaMenu = getItem(position).getName();
        int qty = getItem(position).getQty();
        int harga = getItem(position).getPrice();

        Order or = new Order(namaMenu, qty, harga);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView menuTxt = (TextView) convertView.findViewById(R.id.menu);
        TextView qtyTxt = (TextView) convertView.findViewById(R.id.qtyTxt);
        Button delete = (Button) convertView.findViewById(R.id.deleteBtn);

        menuTxt.setText(namaMenu);
        qtyTxt.setText("Rp. " + harga +" x "+ qty);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ar.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
