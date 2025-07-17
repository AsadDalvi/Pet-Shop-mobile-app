package com.example.a20f20317petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class cupdatelastpurchasedate extends AppCompatActivity {

    EditText pttid,newpurchasedate;
    OrderDB20F20317 ODB;
    Button change, backhome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupdatelastpurchasedate);

        pttid=findViewById(R.id.pidtext);
        newpurchasedate=findViewById(R.id.newppurchasedate);
        change=findViewById(R.id.updateLPD);
        ODB=new OrderDB20F20317(this);
        lastpurchasedateupdate();

        backhome=findViewById(R.id.homepage);
        backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(cupdatelastpurchasedate.this, customerp.class);
                startActivity(intent);
            }
        });
    }

    public void lastpurchasedateupdate(){
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cid1=pttid.getText().toString();
                String NPPD=newpurchasedate.getText().toString();
                boolean Newpetproductdate=ODB.lastpurchasedateupdate(NPPD,cid1);
                if (Newpetproductdate==true)
                {
                    Toast.makeText(cupdatelastpurchasedate.this, "Purchase date changed", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(cupdatelastpurchasedate.this,customerp.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(cupdatelastpurchasedate.this, "Purchase date is not changed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}