package com.hfad.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Pay extends AppCompatActivity {

    ArrayList<Order> order;
    ArrayList<Pembelian> pembelian;
    int money;
    ListView lv;
    int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        getSupportActionBar().hide();

        if ((ArrayList<Order>)getIntent().getSerializableExtra("order") != null) {
            Intent intent = this.getIntent();
            order = (ArrayList<Order>) getIntent().getSerializableExtra("order");
            money = getIntent().getIntExtra("money", 0);
        }

        if((ArrayList<Pembelian>) getIntent().getSerializableExtra("pembelian") != null){
            Intent intent = this.getIntent();
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

        lv = (ListView) findViewById(R.id.payList);

        orderListAdapter adapter = new orderListAdapter(this, R.layout.pay_view, order);
        lv.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {

        Intent  intent = new Intent();

        intent.putExtra("money", money);
        intent.putExtra("pembelian", (Serializable) pembelian);

        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    public void mainMenu(View view) {

        Intent  intent = new Intent();

        intent.putExtra("money", money);
        intent.putExtra("pembelian", (Serializable) pembelian);

        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}