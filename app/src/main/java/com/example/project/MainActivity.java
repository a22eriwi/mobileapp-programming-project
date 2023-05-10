package com.example.project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=brom";

    private ArrayList<datan> datalistan;
    private RecyclerViewAdapter Adapter;
    private RecyclerView RecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new JsonTask(this).execute(JSON_URL);

        RecyclerView = findViewById(R.id.recyclerView);
        datalistan = new ArrayList<datan>();
        Adapter = new RecyclerViewAdapter(datalistan);
        RecyclerView.setAdapter(Adapter);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onPostExecute(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<datan>>() {
        }.getType();
        ArrayList<datan> data = gson.fromJson(json, type);
        datalistan.addAll(data);
        Adapter.notifyDataSetChanged();
    }
}
