package com.example.a20f20317petshop;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.Toast;

public class addcustomer extends AppCompatActivity {
    RadioButton rfb1, rab2;
    EditText custtype1;
    EditText custid1, custname1, typecust1, custpass;
    Button addcust, backhome, clearall;
    RegisteringDB20F20317 RDB3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcustomer);

        rfb1=findViewById(R.id.r1);
        rab2=findViewById(R.id.r2);
        custtype1=findViewById(R.id.custtype);

        rfb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { custtype1.setText("Pet owner"); }
        });

        rab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { custtype1.setText("Vet professional"); }
        });

        custid1=findViewById(R.id.idtext);
        custname1=findViewById(R.id.nametext);
        typecust1=findViewById(R.id.custtype);
        custpass=findViewById(R.id.custpassword);
        addcust=findViewById(R.id.addcustomer);
        clearall=findViewById(R.id.clearall);

        clearall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                custid1.setText("");
                custname1.setText("");
                typecust1.setText("");
                custpass.setText("");
            }
        });

        addcustomer();
        RDB3=new RegisteringDB20F20317(this);

        backhome=findViewById(R.id.homepage);
        backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(addcustomer.this, admin20F20317.class);
                startActivity(intent);
            }
        });
    }

    public void addcustomer(){
        addcust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cid2=custid1.getText().toString();
                String cn2=custname1.getText().toString();
                String ct2=custtype1.getText().toString();
                String cp2=custpass.getText().toString();
                boolean add=RDB3.addcustomer(cid2,cn2,ct2,cp2);
                if(add){
                    Toast.makeText(addcustomer.this,"Customer added", Toast.LENGTH_SHORT).show();
                    Intent login=new Intent(addcustomer.this,login20F20317.class);
                    startActivity(login);
                } else {
                    Toast.makeText(addcustomer.this,"Customer already added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
