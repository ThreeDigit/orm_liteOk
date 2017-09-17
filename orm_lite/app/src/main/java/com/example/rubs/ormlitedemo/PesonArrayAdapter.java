package com.example.rubs.ormlitedemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

class PesonArrayAdapter extends ArrayAdapter<String> {

    private LayoutInflater layoutInflater;
    private List list;

    public PesonArrayAdapter(Context context,
                             int resource, List objects) {

        super(context, resource, objects);
        this.list = objects;
        layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = layoutInflater.inflate(R.layout.list_view, parent, false);


        if (list.get(position).getClass().isInstance(new Person())) {

            final Person information_model = (Person) list.get(position);
            ((TextView)convertView.findViewById(R.id.name)).
                    setText(information_model.getName());
            ((TextView)convertView.findViewById(R.id.email)).
                    setText(information_model.getEmail());
        }
        return convertView;
    }
}
