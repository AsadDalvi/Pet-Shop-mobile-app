package com.example.a20f20317petshop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin20F20317 extends AppCompatActivity {
    Button addcustt, viewcdt, viewaprd, closeapp, logoutbtn;
    RegisteringDB20F20317 RDB2;
    OrderDB20F20317 ODB1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin20_f20317);

        addcustt = findViewById(R.id.addcust);
        viewcdt = findViewById(R.id.custview);
        viewaprd = findViewById(R.id.viewallp);
        closeapp = findViewById(R.id.exitapp);
        logoutbtn = findViewById(R.id.alogout);

        addcustt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin20F20317.this, addcustomer.class);
                startActivity(intent);
            }
        });

        closeapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin20F20317.this, login20F20317.class);
                startActivity(intent);
                finish(); //for closing activity
            }
        });

        RDB2 = new RegisteringDB20F20317(this);
        getaccounts();

        ODB1 = new OrderDB20F20317(this);
        viewallproducts();
    }

    public void viewinformations(String title, String mes) {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setCancelable(true);
        ad.setTitle(title);
        ad.setMessage(mes);
        ad.show();
    }

    public void getaccounts() {
        viewaprd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cr2 = RDB2.getaccounts();
                if (cr2.getCount() == 0) {
                    viewinformations("Not Found", "Cannot find pet products");
                    return;
                }
                StringBuffer SB1 = new StringBuffer();
                while (cr2.moveToNext()) {
                    SB1.append("*---*---*---*---*---*---*---*" + "\n");
                    SB1.append("Customer ID :- " + cr2.getString(0) + "\n");
                    SB1.append("Customer Name :- " + cr2.getString(1) + "\n");
                    SB1.append("Type of Customer :- " + cr2.getString(2) + "\n");
                    SB1.append("Customer Password :- " + cr2.getString(3) + "\n");
                    SB1.append("**--**--**--**--**--**--**--**" + "\n");
                }
                viewinformations("Customer Information", SB1.toString());
            }
        });
    }

    public void viewallproducts() {
        viewcdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cr3 = ODB1.viewallproducts();
                if (cr3.getCount() == 0) {
                    viewinformations("Product not found", "No products added");
                    return;
                }
                StringBuffer SB3 = new StringBuffer();
                while (cr3.moveToNext()) {
                    SB3.append("*---*---*---*---*---*---*---*" + "\n");
                    SB3.append("Product ID: " + cr3.getString(0) + "\n");
                    SB3.append("Total Cost: " + cr3.getString(1) + "\n");
                    SB3.append("Total Product: " + cr3.getString(2) + "\n");
                    SB3.append("Purchase Date: " + cr3.getString(3) + "\n");
                    SB3.append("Pet Name/ID: " + cr3.getString(4) + "\n");
                    SB3.append("**--**--**--**--**--**--**--**" + "\n");
                }
                viewinformations("Pet Product Information", SB3.toString());
            }
        });
    }
}

