package com.example.poke_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class ViewDatabaseListView extends AppCompatActivity {

    private ListView pokemonListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_database_list_view);

        pokemonListView = findViewById(R.id.pokemonListView);
    }
}