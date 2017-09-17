package com.example.rubs.ormlitedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    private EditText edName;
    private EditText edEmail;
    private Button btnUpdate;
    private String id;
    private Bundle extras;
    private CrudOperations crudOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edName = (EditText) findViewById(R.id.edAddName);
        edEmail = (EditText) findViewById(R.id.edAddEmail);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        extras = getIntent().getExtras();
        crudOperations = new CrudOperations(this);
        setEditTexts();
    }

    private void setEditTexts() {
        if (extras != null) {
            id = extras.getString("obj_id");
        }

        Person person = crudOperations.getSingleRecord(Integer.valueOf(id));
        if (person != null){
            edEmail.setText(person.getEmail());
            edName.setText(person.getName());
        }
    }
}
