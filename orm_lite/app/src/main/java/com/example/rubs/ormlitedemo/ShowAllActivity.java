package com.example.rubs.ormlitedemo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class ShowAllActivity extends
        AppCompatActivity implements AdapterView.OnItemLongClickListener {

    private ListView listView;
    private List<Person> arrPersonList;
    private CrudOperations crudOperations;
    private LayoutInflater layoutInflater;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);
        crudOperations = new CrudOperations(this);
        listView = (ListView) findViewById(R.id.listview);
        setListView();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view,
                                   int position, long id) {
        if (position > 0) {
            openDialog(position - 1);
        }
        return false;
    }


    private void setListView() {

        arrPersonList = crudOperations.getAllData();
        layoutInflater = (LayoutInflater)
                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = layoutInflater.inflate(R.layout.list_view, listView, false);

        listView.setAdapter(new PesonArrayAdapter(
                this, R.layout.list_view, arrPersonList));

        listView.addHeaderView(view);
        listView.setOnItemLongClickListener(this);
    }

    public void openDialog(final int position) {

        final AlertDialog.Builder
                alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setMessage("Do you want to update this record?");

        alertDialogBuilder.setTitle("Update");

        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

       if (arrPersonList.get(position) != null) {

                   Intent intent =
                           new Intent(getApplicationContext(),
                                   UpdateActivity.class);

                   intent.putExtra("obj_id",
                           String.valueOf(arrPersonList.
                                   get(position).getId()));

                            startActivity(intent);
                        }
                    }
                });

        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        crudOperations.closeDb();
    }
}
