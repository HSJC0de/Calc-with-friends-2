package com.example.calculatorwithfriends;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    String currInput = "";
    ArrayList<Double> history = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button historyButton = findViewById(R.id.historyButton);

        historyButton.setOnClickListener(v -> {
            // 👇 PROOF CLICK WORKS
            Toast.makeText(this, "History button clicked", Toast.LENGTH_SHORT).show();

            // 👇 TRY TO OPEN HISTORY ACTIVITY
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);
        });
    }
    public void onButtonClicked(@NonNull View view){
        //Adds value of view to currInput
        String symbol = (String)((TextView)view).getText();
        System.out.println("symbol: " + symbol);
        if(symbol.equals("=")){
            //Call evaluate(), store result in string
            String result = evaluate();
            //Call displayResult(result), which displays result in activity_main
            displayResult(result);
            //Call saveToHistory(result), which passes result to the history ArrayList
            //saveToHistory(result);
            currInput = "";
        }
        else if(symbol.equals("Ans")){
            //Obtains previous answer from history ArrayList if history is not empty
            if(!history.isEmpty()){
                String prevAns = history.get(history.size() - 1).toString();
                currInput += prevAns;
            }
        }
        else{
            currInput += symbol;
            System.out.println("currInput: " + currInput);
        }
    }
    protected String evaluate(){
        //Call outside library to evaluate currInput
        //String result = OUTSIDE LIBRARY FUNCTION;
        //return result;
        return "hi";
    }
    protected void displayResult(String value){
        //Modifies contents of current gray box in activity_main.xml to be the answer
        //Value v = getValueById(ID OF GRAY BOX);
        //v.text = value;
        currInput = value;
    }
    protected void saveToHistory(String value){
        //Stores value into history ArrayList
        history.add(Double.valueOf(value));
    }
}