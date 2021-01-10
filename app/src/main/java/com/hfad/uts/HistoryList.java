package com.hfad.uts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryList extends ArrayAdapter<Pembelian> {

    private  static final String  TAG = "orderListAdapter";
    private Context mContext;
    int mResource;
    ArrayList<Pembelian> ar;

    public HistoryList(Context context, int resource, ArrayList<Pembelian> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        ar = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Date tanggal = getItem(position).getTanggalPembelian();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy hh:mm:ss");
        String stringTanggal = formatter.format(tanggal);

        ArrayList<Order> or = new ArrayList<Order>();
        int size = getItem(position).getAl().size();
        for(int i = 0; i < size; i++){
            Order o = new Order(getItem(position).getAl().get(i).getName(), getItem(position).getAl().get(i).getQty(), getItem(position).getAl().get(i).getPrice());
            or.add(o);
        }
        String alamat = getItem(position).getAlamat();
        int total = getItem(position).getTotal();

        Pembelian p = new Pembelian(tanggal, alamat, total, or);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tanggalTxt = (TextView) convertView.findViewById(R.id.tanggalTxt);

        ListView lv = (ListView) convertView.findViewById(R.id.listPembelian);
        orderListAdapter adapter = new orderListAdapter(mContext, R.layout.pay_view, or);
        lv.setAdapter(adapter);

        TextView alamatTxt = (TextView) convertView.findViewById(R.id.alamatTxt);
        TextView totalTxt = (TextView) convertView.findViewById(R.id.totalHistoryTxt);

        tanggalTxt.setText(stringTanggal);
        alamatTxt.setText("Alamat : " + alamat);
        totalTxt.setText("Rp. " + total);

        return convertView;
    }
}
