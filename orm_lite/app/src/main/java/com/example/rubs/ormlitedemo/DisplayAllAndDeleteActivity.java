package com.example.rubs.ormlitedemo;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.List;

public class DisplayAllAndDeleteActivity
        extends AppCompatActivity
        implements AdapterView.OnItemLongClickListener {

    private ListView listView;
    private int selectPosition = -1;
    private List<Person> informationList;
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
            selectPosition = position - 1;
            showDialog();
        }
        return false;
    }


    private void setListView() {

        informationList = crudOperations.getAllData();

        layoutInflater = (LayoutInflater)
                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = layoutInflater.inflate(R.layout.list_view, listView, false);

        listView.setAdapter(new PesonArrayAdapter(
                this, R.layout.list_view, informationList));

        listView.addHeaderView(view);
        listView.setOnItemLongClickListener(this);
    }

    private void showDialog() {

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you want to delete this record?");
        alertDialogBuilder.setTitle("Delete");

        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        crudOperations.deleteData(informationList.
                                get(selectPosition));

                        informationList.remove(selectPosition);

                        //Re Draw All views
                        listView.invalidateViews();
                        selectPosition = -1;
                    }
                });

        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(
                            DialogInterface dialog, int which) {

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
