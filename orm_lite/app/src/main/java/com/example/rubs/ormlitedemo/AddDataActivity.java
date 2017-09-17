package com.example.rubs.ormlitedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDataActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edName, edEmail;
    private Button btnAdd;
    private CrudOperations crudOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_insert_data);
        edName = (EditText) findViewById(R.id.edAddName);
        edEmail = (EditText) findViewById(R.id.edAddEmail);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        crudOperations = new CrudOperations(this);
        btnAdd.setOnClickListener(this);
    }

    private void reset() {
        edName.setText("");
        edEmail.setText("");
    }

    @Override
    public void onClick(View v) {

        int id;
        Person personObj;

        if (edName.getText().toString().trim().length() > 0
                && edEmail.getText().toString().trim().length() > 0) {

    personObj =
            new Person
        (edName.getText().toString(), edEmail.getText().toString());

            id = crudOperations.insertData(personObj);

            if (id != -1) {
            Toast.makeText(getBaseContext(), "Data Added sucessfully",
                        Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getBaseContext(), "Data not Added",
                        Toast.LENGTH_SHORT).show();
            }

            reset();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        crudOperations.closeDb();
    }
}

