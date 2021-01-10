package com.hfad.uts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class MyOrder extends AppCompatActivity {

    ArrayList<Order> order;
    ArrayList<Pembelian> pembelian;
    int money;
    ListView lv;
    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        getSupportActionBar().hide();

        if ((ArrayList<Order>)getIntent().getSerializableExtra("order") != null) {
            Intent intent = this.getIntent();
            order = (ArrayList<Order>) getIntent().getSerializableExtra("order");
            money = getIntent().getIntExtra("money", 0);
        }

        if((ArrayList<Pembelian>) getIntent().getSerializableExtra("pembelian") != null){
            pembelian = (ArrayList<Pembelian>) getIntent().getSerializableExtra("pembelian");
        }

        total = 0;
        for (int i = 0; i < order.size();i++){
            int price = order.get(i).getPrice();
            int banyak = order.get(i).getQty();
            total = total + (price * banyak);
        }
        TextView totalTxt = findViewById(R.id.totalTxt);
        totalTxt.setText("Total : Rp. " + total);

        lv = (ListView) findViewById(R.id.listId);

        orderListButton adapter = new orderListButton(this, R.layout.adapter_view, order);
        lv.setAdapter(adapter);
    }

    public void pay(View view) {
        if (money < total){
            Toast.makeText(this, "Uang anda tidak cukup", Toast.LENGTH_SHORT).show();
        }else if (order.size() == 0){
            Toast.makeText(this, "Tidak ada barang", Toast.LENGTH_SHORT).show();
        }
        else {
            money = money - total;

            Date dates = new Date();
            String alamat = "alamat tidak diisi";
            Pembelian p = new Pembelian(dates, alamat, total, order);
            pembelian.add(p);

            Intent intent = new Intent(this, Pay.class);

            intent.putExtra("order", (Serializable) order);
            intent.putExtra("pembelian", (Serializable) pembelian);
            intent.putExtra("money", money);

            startActivityForResult(intent, 0);
        }
    }

    @Override
    public void onBackPressed() {
        Intent  intent = new Intent();

        intent.putExtra("order", (Serializable) order);
        intent.putExtra("pembelian", (Serializable) pembelian);
        intent.putExtra("money", money);

        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            if(resultCode == Activity.RESULT_OK){
                money = data.getIntExtra("money", 0);
                pembelian = (ArrayList<Pembelian>) data.getSerializableExtra("pembelian");

                Intent  intent = new Intent();

                intent.putExtra("money", money);
                intent.putExtra("pembelian", (Serializable) pembelian);

                setResult(Activity.RESULT_CANCELED, intent);
                finish();
            }
        }if(requestCode == 6){
            if(resultCode == Activity.RESULT_OK){
                order = (ArrayList<Order>) data.getSerializableExtra("order");
                pembelian = (ArrayList<Pembelian>) data.getSerializableExtra("pembelian");
                money = data.getIntExtra("money", 0);
            }
        }
    }

    public void history(View view) {
        Intent  intent = new Intent(this, History.class);

        intent.putExtra("order", (Serializable) order);
        intent.putExtra("pembelian", (Serializable) pembelian);
        intent.putExtra("money", money);

        startActivityForResult(intent, 6);
    }
}