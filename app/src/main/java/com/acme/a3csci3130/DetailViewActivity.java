package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DetailViewActivity extends Activity {

    private EditText nameField, businessNumField, primaryBusinessField, addressField, provinceField;
    Contact receivedPersonInfo, selectedBusiness;
    private DatabaseReference Database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Database = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");

        nameField = (EditText) findViewById(R.id.name);
        businessNumField = (EditText) findViewById(R.id.businessNum);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            businessNumField.setText(receivedPersonInfo.businessNum);
            primaryBusinessField.setText(receivedPersonInfo.primaryBusiness);
            addressField.setText(receivedPersonInfo.address);
            provinceField.setText(receivedPersonInfo.province);
        }
    }

    /**
     * Updates contact fields and saves to db
     * @param v
     */
    public void updateContact(View v){
        DatabaseReference contactsRef = Database.child("contacts");
        selectedBusiness = (Contact)getIntent().getSerializableExtra("Contact");
        DatabaseReference selectedContactRef = contactsRef.child(selectedBusiness.uid);
        String name = nameField.getText().toString();
        String businessNum = businessNumField.getText().toString();
        String primaryBusiness = primaryBusinessField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();
        Map<String, Object> updates = new HashMap<String, Object>();
        updates.put("businessNumber", businessNum);
        updates.put("name", name);
        updates.put("primaryBusiness", primaryBusiness);
        updates.put("address", address);
        updates.put("province", province);
        selectedContactRef.updateChildren(updates);
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * erases contact from DB
     * @param v
     */
    public void eraseContact(View v)
    {
        DatabaseReference contactsRef = Database.child("contacts");
        selectedBusiness = (Contact)getIntent().getSerializableExtra("Contact");
        contactsRef.child(selectedBusiness.uid).removeValue();
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
