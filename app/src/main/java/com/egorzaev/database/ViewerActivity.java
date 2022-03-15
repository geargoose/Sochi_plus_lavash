package com.egorzaev.database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ViewerActivity extends AppCompatActivity {

    Spinner field;
    ListView listView;
    EditText request;
    Button search;
    ArrayList<String> items;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);

        field = findViewById(R.id.spinner);
        search = findViewById(R.id.button);
        request = findViewById(R.id.searchField);
        listView = findViewById(R.id.itemsList);

        Db db = new Db(getBaseContext(), "Vegetables", null, 1);
        SQLiteDatabase projects = db.getReadableDatabase();

        field.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.fields)));

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor c = projects.query("projects", null, request.getText().toString(), new String[]{getResources().getStringArray(R.array.fields)[field.getSelectedItemPosition()]}, null, null, null);

                if (c.getCount() > 0) {
                    c.moveToFirst();
                    do {
                        items.add("" + c.getInt(0) + " " + c.getString(1) + " " + c.getString(2) + " " + c.getString(3) + " " + c.getString(4) + "\n");
                    } while (c.moveToNext());
                }

                c.close();
                adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, items);
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
            }
        });

    }
}