package com.example.a20f20317petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class customerp extends AppCompatActivity {

    Button addprd, deleteprd, updateLPD, searchprd, lgot, closeapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerp);

        addprd=findViewById(R.id.addprod);

        addprd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(customerp.this, addproduct.class);
                startActivity(intent);
            }
        });

        deleteprd=findViewById(R.id.deletep);
        deleteprd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(customerp.this, deletepetproducts.class);
                startActivity(intent);
            }
        });

        updateLPD=findViewById(R.id.updateLPdate);
        updateLPD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(customerp.this, cupdatelastpurchasedate.class);
                startActivity(intent);
            }
        });

        searchprd=findViewById(R.id.searchp11);
        searchprd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(customerp.this, searchproducts.class);
                startActivity(intent);
            }
        });

        lgot=findViewById(R.id.logoutc);
        lgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(customerp.this, login20F20317.class);
                startActivity(intent);
            }
        });

        closeapp=findViewById(R.id.exitapp);
        closeapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }
}