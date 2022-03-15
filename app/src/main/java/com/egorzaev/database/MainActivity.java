package com.egorzaev.database;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button go, search;
    EditText name, descr, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Db db = new Db(getBaseContext(), "Vegetables", null, 1);
        SQLiteDatabase projects = db.getWritableDatabase();

        go = findViewById(R.id.add);
        title = findViewById(R.id.editTextTitle);
        name = findViewById(R.id.editTextPersonName);
        descr = findViewById(R.id.editTextDescription);
        search = findViewById(R.id.go_to_search);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date now = new Date();
                //projects.query("projects");
                projects.execSQL(
                        "INSERT INTO projects (title, name, description, date) VALUES (\"" + title.getText() +
                                "\", \"" + name.getText() +
                                "\", \"" + descr.getText() +
                                "\", \"" + now.toString() + "\")"
                );
                Toast.makeText(MainActivity.this, "Ура! Вам повезло! Ваша запись добавлена в базу данных.", Toast.LENGTH_LONG).show();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewerActivity.class));
            }
        });
    }
}

