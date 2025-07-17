package com.example.a20f20317petshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class deletepetproducts extends AppCompatActivity {

    Button backhome,deletep;
    EditText cid;
    OrderDB20F20317 ODB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletepetproducts);

        backhome=findViewById(R.id.homepage);
        backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(deletepetproducts.this, customerp.class);
                startActivity(intent);
            }
        });

        cid=findViewById(R.id.idtext);
        deletep=findViewById(R.id.delete);

        ODB=new OrderDB20F20317(this);

        deletepetproduct();

    }

    public void deletepetproduct(){
        deletep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ciid=cid.getText().toString();
                Integer cdelete=ODB.deletepetproducts(ciid);
                if(cdelete>0){
                    Toast.makeText(deletepetproducts.this,"Product Deleted",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(deletepetproducts.this,customerp.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(deletepetproducts.this,"Cannot Delete",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}