package com.example.a20f20317petshop;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class addproduct extends AppCompatActivity {
    EditText f1a, f1q, a2a, a2q, totalc;
    EditText csid, prcdate, tqty, cstotal, pnid;
    CheckBox cbb1, cbb2;
    Button tcalcc, addpr, clear;
    EditText totalqty, ff1, aa2;

    private foodcalc f1calc;
    private accessoriescalc a1calc;
    private totalpaymentcalc tctc;

    String n1,n2;

    OrderDB20F20317 odb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproduct);

        cbb1=findViewById(R.id.checkbox1);
        f1a=findViewById(R.id.foodtextc);
        f1q=findViewById(R.id.foodqtytext);

        cbb2=findViewById(R.id.checkbox2);
        a2a=findViewById(R.id.accsrtextc);
        a2q=findViewById(R.id.accsrtextq);

        totalqty=findViewById(R.id.totalitems);
        ff1=findViewById(R.id.foodqtytext);
        aa2=findViewById(R.id.accsrtextq);

        cbb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f1a.setText("3");
                n1="Food";
            }
        });

        cbb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a2a.setText("3");
                n2="Accessories";
            }
        });

        f1calc=new foodcalc();
        a1calc=new accessoriescalc();
        tctc=new totalpaymentcalc();

        //total calculation of product with quantity
        totalc=findViewById(R.id.totaltext);
        tcalcc=findViewById(R.id.calculatetot);
        tcalcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String f11=f1a.getText().toString();
                String a22=a2a.getText().toString();

                String fq11=f1q.getText().toString();
                String aq22=a2q.getText().toString();

                //converting double
                Double fcc1=Double.parseDouble(f11);
                Double acc2=Double.parseDouble(a22);

                Double fqq1=Double.parseDouble(fq11);
                Double aqq2=Double.parseDouble(aq22);

                Double fcalc1=f1calc.foodcalculation(fcc1, fqq1);
                Double acalc2=a1calc.accessoriescalculation(acc2, aqq2);

                //calculation of total product cost with quantity
                Double tcc= tctc.totalpaymentcalculation(fcalc1, acalc2);

                totalc.setText(""+tcc);
                totalc.setVisibility(View.VISIBLE);
                totalqty.setText(n1+"-"+fq11+","+n2+"-"+aq22);
            }
        });
        //codes for adding products into database
        addpr=findViewById(R.id.pconfirm);
        csid=findViewById(R.id.idtext);
        cstotal=findViewById(R.id.totaltext);
        tqty=findViewById(R.id.totalitems);
        prcdate=findViewById(R.id.purchasedate);
        pnid=findViewById(R.id.petnameidtext);

        clear=findViewById(R.id.clearall);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalqty.setText("");
                tqty.setText("");
                csid.setText("");
                prcdate.setText("");
                pnid.setText("");
                f1a.setText("");
                f1q.setText("");
                a2a.setText("");
                a2q.setText("");
                totalc.setText("");
            }
        });
        odb=new OrderDB20F20317(this);
        addproducts();
    }
    public void addproducts(){
        addpr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String c1id=csid.getText().toString();
                String tc1=cstotal.getText().toString();
                String tq1=tqty.getText().toString();
                String date1=prcdate.getText().toString();
                String pnid1=pnid.getText().toString();
                boolean add=odb.addproducts(c1id, tc1, tq1, date1, pnid1);
                if(add==true)
                {
                    Toast.makeText(addproduct.this,"Product has been added", Toast.LENGTH_SHORT).show();
                    Intent login=new Intent(addproduct.this,customerp.class);
                    startActivity(login);
                }
                else
                {
                    Toast.makeText(addproduct.this,"Product not added",Toast.LENGTH_SHORT).show();
                }}
        });
    }}