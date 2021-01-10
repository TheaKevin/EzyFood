package com.hfad.uts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class InputOrder extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Order> order;
    ArrayList<Pembelian> pembelian;
    EditText qty;
    Button MyOrder, OrderMore;
    String quantity, menu;
    int quan = 0;
    int val = 0;
    int harga;
    int money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_order);
        getSupportActionBar().hide();
        val = 0;

        if ((ArrayList<Order>)getIntent().getSerializableExtra("order") != null){
            Intent intent = this.getIntent();
            order = (ArrayList<Order>) getIntent().getSerializableExtra("order");
            money = getIntent().getIntExtra("money", 0);
            menu = getIntent().getStringExtra("pesanan");
            harga = intent.getIntExtra("harga", 0);
            TextView txt;
            txt = findViewById(R.id.menuTxt);
            txt.setText(menu + "\nRp. " + harga);
        }

        if((ArrayList<Pembelian>) getIntent().getSerializableExtra("pembelian") != null){
            Intent intent = this.getIntent();
            pembelian = (ArrayList<Pembelian>) getIntent().getSerializableExtra("pembelian");
        }

        qty = findViewById(R.id.qty);
        MyOrder = findViewById(R.id.orderBtn);
        OrderMore  = findViewById(R.id.moreBtn);
        MyOrder.setOnClickListener(this);
        OrderMore.setOnClickListener(this);

    }

    public void order() {
        Intent  intent = new Intent(this, MyOrder.class);

        intent.putExtra("order", (Serializable) order);
        intent.putExtra("pembelian", (Serializable) pembelian);
        intent.putExtra("money", money);

        startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 10){
            if(resultCode == Activity.RESULT_OK){
                order = (ArrayList<Order>) data.getSerializableExtra("order");
                pembelian = (ArrayList<Pembelian>) data.getSerializableExtra("pembelian");
                money = data.getIntExtra("money", 0);

                Intent  intent = new Intent();

                intent.putExtra("order", (Serializable) order);
                intent.putExtra("pembelian", (Serializable) pembelian);
                intent.putExtra("money", money);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
            else if (resultCode == Activity.RESULT_CANCELED){
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

                Intent  intent = new Intent();

                intent.putExtra("order", (Serializable) order);
                intent.putExtra("pembelian", (Serializable) pembelian);
                intent.putExtra("money", money);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
            else if (resultCode == Activity.RESULT_CANCELED){
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
    public void onClick(View v) {
        val = 0;
        validasiData();
        if(val == 0){
            inputData();
            switch (v.getId()){
                case R.id.orderBtn:
                    order();
                    break;
                case R.id.moreBtn:
                    more();
                    break;
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

    private void more() {
        Intent  intent = new Intent();

        intent.putExtra("order", (Serializable) order);
        intent.putExtra("pembelian", (Serializable) pembelian);
        intent.putExtra("money", money);

        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void inputData() {
        int ada = -1;
        for(int i = 0; i < order.size();i++){
            if(menu.equals(order.get(i).getName())){
                ada = i;
                break;
            }
        }
        if(ada != -1){
            int qtyBefore = order.get(ada).getQty() + quan;
            order.get(ada).setQty(qtyBefore);
        }
        else{
            Order newOrder = new Order(menu, quan, harga);
            order.add(newOrder);
        }
    }

    private void validasiData() {
        quantity = qty.getText().toString();
        if (quantity.length() == 0){
            Toast.makeText(this, "Quantity tidak boleh kosong", Toast.LENGTH_SHORT).show();
            val = 1;
            return;
        }
        try {
            quan = Integer.parseInt(quantity);
        }catch (Exception e){
            Toast.makeText(this, "Quantity harus angka", Toast.LENGTH_SHORT).show();
            val = 1;
            return;
        }
        if(quan <= 0){
            Toast.makeText(this, "Quantity harus lebih dari 0", Toast.LENGTH_SHORT).show();
            val = 1;
            return;
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