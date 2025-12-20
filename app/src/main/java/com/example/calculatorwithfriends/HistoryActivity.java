package com.example.calculatorwithfriends;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Find the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.historyRecyclerView);

        // Make it a vertical scrolling list
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // TEMP data so we can see something on screen
        ArrayList<String> history = new ArrayList<>();
        history.add("12 + 3 = 15");
        history.add("5 × 6 = 30");
        history.add("100 ÷ 4 = 25");

        // Connect adapter
        HistoryAdapter adapter = new HistoryAdapter(history);
        recyclerView.setAdapter(adapter);
    }
}