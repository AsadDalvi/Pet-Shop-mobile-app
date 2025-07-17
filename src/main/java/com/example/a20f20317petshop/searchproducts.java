package com.example.a20f20317petshop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class searchproducts extends AppCompatActivity {
    Button backhome, search1;
    EditText pn1id;
    OrderDB20F20317 odb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchproducts);

        // Initialize views
        backhome = findViewById(R.id.homepage);
        search1 = findViewById(R.id.searchp22);
        pn1id = findViewById(R.id.pnidt);
        odb = new OrderDB20F20317(this);

        // Set Listener for Home button
        backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to home
                Intent intent = new Intent(searchproducts.this, customerp.class);
                startActivity(intent);
            }
        });

        // Set Listener for Search button
        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the method to search products
                viewproduct();
            }
        });
    }
    public void viewproduct() {
        String pnnid1 = pn1id.getText().toString().trim(); // Ensure no trailing spaces
        if (pnnid1.isEmpty()) {
            showpetproducts("Error", "Please enter a valid Pet Name/ID.");
            return;
        }

        // Query the database
        Cursor cfa1 = odb.viewproduct(pnnid1);

        if (cfa1.getCount() == 0) {
            showpetproducts("Not Found", "Cannot find pet products.");
            return;
        }

        StringBuffer SB = new StringBuffer();
        while (cfa1.moveToNext()) {
            SB.append("*---*---*---*---*---*---*---*" + "\n");
            SB.append("Product ID: " + cfa1.getString(0) + "\n");
            SB.append("Total Cost: " + cfa1.getString(1) + "\n");
            SB.append("Total Product: " + cfa1.getString(2) + "\n");
            SB.append("Purchase Date: " + cfa1.getString(3) + "\n");
            SB.append("Pet Name/ID: " + cfa1.getString(4) + "\n");
            SB.append("**--**--**--**--**--**--**--**" + "\n");
        }
        showpetproducts("Pet Product Information", SB.toString());
    }

    public void showpetproducts(String title, String mes) {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setCancelable(true);
        ad.setTitle(title);
        ad.setMessage(mes);
        ad.show();
    }
}
