package com.hfad.uts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Order> order = new ArrayList<Order>();
    ArrayList<Pembelian> pembelian = new ArrayList<Pembelian>();
    int money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        if ((ArrayList<Order>)getIntent().getSerializableExtra("order") != null) {
            order = (ArrayList<Order>) getIntent().getSerializableExtra("order");
            money = getIntent().getIntExtra("money", 0);
        }

        if((ArrayList<Pembelian>) getIntent().getSerializableExtra("pembelian") != null){
            pembelian = (ArrayList<Pembelian>) getIntent().getSerializableExtra("pembelian");
        }

        TextView moneyTxt = findViewById(R.id.moneyTxt);
        moneyTxt.setText("Balance : Rp. " + money);
    }

    public void drink(View view) {
        Intent  intent = new Intent(this, Drinks.class);

        intent.putExtra("order", (Serializable) order);
        intent.putExtra("pembelian", (Serializable) pembelian);
        intent.putExtra("money", money);

        startActivityForResult(intent, 4);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 4){
            if(resultCode == Activity.RESULT_OK){
                order = (ArrayList<Order>) data.getSerializableExtra("order");
            }else if (resultCode == Activity.RESULT_CANCELED){
                order = new ArrayList<Order>();
            }
        }else if(requestCode == 11){
            if(resultCode == Activity.RESULT_OK){
                order = (ArrayList<Order>) data.getSerializableExtra("order");
            }else if (resultCode == Activity.RESULT_CANCELED){
                order = new ArrayList<Order>();
            }
        }else if (requestCode == 5){
            if(resultCode == Activity.RESULT_OK){
                order = (ArrayList<Order>) data.getSerializableExtra("order");
            }else if (resultCode == Activity.RESULT_CANCELED){
                order = new ArrayList<Order>();
            }
        }else if (requestCode == 6){
            if(resultCode == Activity.RESULT_OK){
                order = (ArrayList<Order>) data.getSerializableExtra("order");
            }else if (resultCode == Activity.RESULT_CANCELED){
                order = new ArrayList<Order>();
            }
        }

        pembelian = (ArrayList<Pembelian>) data.getSerializableExtra("pembelian");
        money = data.getIntExtra("money", 0);
        TextView moneyTxt = findViewById(R.id.moneyTxt);
        moneyTxt.setText("Balance : Rp. " + money);

    }

    public void order(View view) {
        Intent  intent = new Intent(this, MyOrder.class);

        intent.putExtra("order", (Serializable) order);
        intent.putExtra("pembelian", (Serializable) pembelian);
        intent.putExtra("money", money);

        startActivityForResult(intent, 11);
    }

    public void Snacks(View view) {
        Intent  intent = new Intent(this, Snacks.class);

        intent.putExtra("order", (Serializable) order);
        intent.putExtra("pembelian", (Serializable) pembelian);
        intent.putExtra("money", money);

        startActivityForResult(intent, 4);
    }

    public void Foods(View view) {
        Intent  intent = new Intent(this, Foods.class);

        intent.putExtra("order", (Serializable) order);
        intent.putExtra("pembelian", (Serializable) pembelian);
        intent.putExtra("money", money);

        startActivityForResult(intent, 4);
    }

    public void TopUp(View view) {
        Intent  intent = new Intent(this, TopUp.class);

        intent.putExtra("order", (Serializable) order);
        intent.putExtra("pembelian", (Serializable) pembelian);
        intent.putExtra("money", money);

        startActivityForResult(intent, 5);
    }

    public void history(View view) {
        Intent  intent = new Intent(this, History.class);

        intent.putExtra("order", (Serializable) order);
        intent.putExtra("pembelian", (Serializable) pembelian);
        intent.putExtra("money", money);

        startActivityForResult(intent, 6);
    }
}