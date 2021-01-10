package com.hfad.uts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class Drinks extends AppCompatActivity{

    ArrayList<Order> order;
    ArrayList<Pembelian> pembelian;
    int money;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);
        getSupportActionBar().hide();

        if ((ArrayList<Order>)getIntent().getSerializableExtra("order") != null){
            Intent intent = this.getIntent();
            order = (ArrayList<Order>) getIntent().getSerializableExtra("order");
            money = getIntent().getIntExtra("money", 0);
        }

        if((ArrayList<Pembelian>) getIntent().getSerializableExtra("pembelian") != null){
            Intent intent = this.getIntent();
            pembelian = (ArrayList<Pembelian>) getIntent().getSerializableExtra("pembelian");
        }
    }

    public void order(View view) {
        Intent  intent = new Intent(this, MyOrder.class);

        intent.putExtra("order", (Serializable) order);
        intent.putExtra("pembelian", (Serializable) pembelian);
        intent.putExtra("money", money);

        startActivityForResult(intent,10);
    }

    public void mineral(View view) {
        Intent  intent = new Intent(this, InputOrder.class);

        intent.putExtra("order", (Serializable) order);
        intent.putExtra("pembelian", (Serializable) pembelian);
        intent.putExtra("pesanan", "Air Mineral");
        intent.putExtra("harga", 3000);
        intent.putExtra("money", money);

        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            if(resultCode == Activity.RESULT_OK){
                order = (ArrayList<Order>) data.getSerializableExtra("order");
                pembelian = (ArrayList<Pembelian>) data.getSerializableExtra("pembelian");
                money = data.getIntExtra("money", 0);
            }else if (resultCode == Activity.RESULT_CANCELED){
                money = data.getIntExtra("money", 0);
                pembelian = (ArrayList<Pembelian>) data.getSerializableExtra("pembelian");

                Intent  intent = new Intent();

                intent.putExtra("money", money);
                intent.putExtra("pembelian", (Serializable) pembelian);

                setResult(Activity.RESULT_CANCELED, intent);
                finish();
            }
        }else if(requestCode == 10){
            if(resultCode == Activity.RESULT_OK){
                order = (ArrayList<Order>) data.getSerializableExtra("order");
                pembelian = (ArrayList<Pembelian>) data.getSerializableExtra("pembelian");
                money = data.getIntExtra("money", 0);
            }else if (resultCode == Activity.RESULT_CANCELED){
                money = data.getIntExtra("money", 0);
                pembelian = (ArrayList<Pembelian>) data.getSerializableExtra("pembelian");

                Intent  intent = new Intent();

                intent.putExtra("money", money);
                intent.putExtra("pembelian", (Serializable) pembelian);

                setResult(Activity.RESULT_CANCELED, intent);
                finish();
            }
        }else if(requestCode == 6){
            if(resultCode == Activity.RESULT_OK){
                order = (ArrayList<Order>) data.getSerializableExtra("order");
                pembelian = (ArrayList<Pembelian>) data.getSerializableExtra("pembelian");
                money = data.getIntExtra("money", 0);
            }else if (resultCode == Activity.RESULT_CANCELED){
                money = data.getIntExtra("money", 0);
                pembelian = (ArrayList<Pembelian>) data.getSerializableExtra("pembelian");

                Intent  intent = new Intent();

                intent.putExtra("money", money);
                intent.putExtra("pembelian", (Serializable) pembelian);

                setResult(Activity.RESULT_CANCELED, intent);
                finish();
            }
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

    public void apel(View view) {
        Intent  intent = new Intent(this, InputOrder.class);

        intent.putExtra("order", (Serializable) order);
        intent.putExtra("pembelian", (Serializable) pembelian);
        intent.putExtra("pesanan", "Jus Apel");
        intent.putExtra("harga", 10000);
        intent.putExtra("money", money);

        startActivityForResult(intent, 0);
    }

    public void mangga(View view) {
        Intent  intent = new Intent(this, InputOrder.class);

        intent.putExtra("order", (Serializable) order);
        intent.putExtra("pembelian", (Serializable) pembelian);
        intent.putExtra("pesanan", "Jus Mangga");
        intent.putExtra("harga", 15000);
        intent.putExtra("money", money);

        startActivityForResult(intent, 0);
    }

    public void alpukat(View view) {
        Intent  intent = new Intent(this, InputOrder.class);

        intent.putExtra("order", (Serializable) order);
        intent.putExtra("pembelian", (Serializable) pembelian);
        intent.putExtra("pesanan", "Jus Alpukat");
        intent.putExtra("harga", 12500);
        intent.putExtra("money", money);

        startActivityForResult(intent, 0);
    }

    public void history(View view) {
        Intent  intent = new Intent(this, History.class);

        intent.putExtra("order", (Serializable) order);
        intent.putExtra("pembelian", (Serializable) pembelian);
        intent.putExtra("money", money);

        startActivityForResult(intent, 6);
    }
}