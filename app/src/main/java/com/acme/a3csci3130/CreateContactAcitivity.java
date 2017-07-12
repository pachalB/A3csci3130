package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateContactAcitivity extends Activity {

    private DatabaseReference Database;
    private Button submitButton;
    private EditText nameField, businessNumField, addressField, primaryBusinessField, provinceField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Database = FirebaseDatabase.getInstance().getReference();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        nameField = (EditText) findViewById(R.id.name);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness);
        businessNumField = (EditText) findViewById(R.id.businessNum);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);

    }

    /**
    * Save contact to db
    * @param v
    **/
    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String personID = appState.firebaseReference.push().getKey();
        String name = nameField.getText().toString();
        String primaryBusiness = primaryBusinessField.getText().toString();
        String businessNum = businessNumField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();
        Contact person = new Contact(personID, name, businessNum, primaryBusiness, address, province);

        appState.firebaseReference.child(personID).setValue(person);

        finish();
    }
}
