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

public class TopUp extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Order> order = new ArrayList<Order>();
    ArrayList<Pembelian> pembelian = new ArrayList<Pembelian>();
    EditText totalTopUp;
    Button topupBtn;
    String ttopup;
    int totTopUp = 0;
    int val = 0;
    int money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
        getSupportActionBar().hide();
        val = 0;

        if ((ArrayList<Order>)getIntent().getSerializableExtra("order") != null){
            Intent intent = this.getIntent();
            order = (ArrayList<Order>) getIntent().getSerializableExtra("order");
            money = getIntent().getIntExtra("money", 0);
        }

        if((ArrayList<Pembelian>) getIntent().getSerializableExtra("pembelian") != null){
            Intent intent = this.getIntent();
            pembelian = (ArrayList<Pembelian>) getIntent().getSerializableExtra("pembelian");
        }

        TextView moneyTxt = findViewById(R.id.moneyTxt);
        moneyTxt.setText("Balance : Rp. " + money);

        totalTopUp = findViewById(R.id.totalTopUp);
        topupBtn = findViewById(R.id.topupBtn);
        topupBtn.setOnClickListener(this);
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
        if(requestCode == 11){
            if(resultCode == Activity.RESULT_OK){
                order = (ArrayList<Order>) data.getSerializableExtra("order");
                pembelian = (ArrayList<Pembelian>) data.getSerializableExtra("pembelian");
                money = data.getIntExtra("money", 0);

                TextView moneyTxt = findViewById(R.id.moneyTxt);
                moneyTxt.setText("Balance : Rp. " + money);
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
        }if(requestCode == 6){
            if(resultCode == Activity.RESULT_OK){
                order = (ArrayList<Order>) data.getSerializableExtra("order");
                pembelian = (ArrayList<Pembelian>) data.getSerializableExtra("pembelian");
                money = data.getIntExtra("money", 0);

                TextView moneyTxt = findViewById(R.id.moneyTxt);
                moneyTxt.setText("Balance : Rp. " + money);
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
        validasi();
        if(val == 0){
            switch (v.getId()){
                case R.id.topupBtn:
                    money = money + totTopUp;
                    totalTopUp.setText("");
                    Toast.makeText(this, "TopUp Berhasil!", Toast.LENGTH_SHORT).show();
                    TextView moneyTxt = findViewById(R.id.moneyTxt);
                    moneyTxt.setText("Balance : Rp. " + money);
                    break;
            }
        }
    }

    private void validasi() {
        ttopup = totalTopUp.getText().toString();
        if (ttopup.length() == 0){
            Toast.makeText(this, "Total TopUp tidak boleh kosong", Toast.LENGTH_SHORT).show();
            val = 1;
            return;
        }
        try {
            totTopUp = Integer.parseInt(ttopup);
        }catch (Exception e){
            Toast.makeText(this, "Total TopUp harus angka", Toast.LENGTH_SHORT).show();
            val = 1;
            return;
        }
        if(totTopUp <= 0){
            Toast.makeText(this, "Total TopUp harus lebih dari 0", Toast.LENGTH_SHORT).show();
            val = 1;
            return;
        }
        if (totTopUp > 2000000){
            Toast.makeText(this, "Total TopUp maksimal Rp. 2.000.000,-", Toast.LENGTH_SHORT).show();
            val = 1;
            return;
        }
    }

    public void order(View view) {
        Intent  intent = new Intent(this, MyOrder.class);

        intent.putExtra("order", (Serializable) order);
        intent.putExtra("pembelian", (Serializable) pembelian);
        intent.putExtra("money", money);

        startActivityForResult(intent, 11);
    }

    public void history(View view) {
        Intent  intent = new Intent(this, History.class);

        intent.putExtra("order", (Serializable) order);
        intent.putExtra("pembelian", (Serializable) pembelian);
        intent.putExtra("money", money);

        startActivityForResult(intent, 6);
    }
}