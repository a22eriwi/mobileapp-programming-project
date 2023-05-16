package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=a22eriwi";

    private ArrayList<Cities> datalistan;
    private RecyclerViewAdapter Adapter;
    private RecyclerView RecyclerView;
    private Button aboutKnapp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new JsonTask(this).execute(JSON_URL);

        RecyclerView = findViewById(R.id.recyclerView);
        datalistan = new ArrayList<Cities>();
        Adapter = new RecyclerViewAdapter(datalistan);
        RecyclerView.setAdapter(Adapter);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        aboutKnapp = findViewById(R.id.knappen1);

        aboutKnapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutThisApp.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onPostExecute(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Cities>>() {
        }.getType();
        ArrayList<Cities> data = gson.fromJson(json, type);
        datalistan.addAll(data);
        Adapter.notifyDataSetChanged();

    }
}
