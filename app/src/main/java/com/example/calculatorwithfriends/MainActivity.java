package com.example.calculatorwithfriends;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;

import java.util.ArrayList;


//Test
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
    }
    protected void addToInput(View v){
        String symbol = v.toString();
        if(symbol.equals("=")){
            //Call evaluate(), store result in string
            String result = evaluate();
            //Call displayResult(result), which displays result in activity_main
            displayResult(result);
            //Call saveToHistory(result), which passes result to the history ArrayList
            saveToHistory(result);
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
        }
    }
    protected String evaluate(){
        //Call outside library to evaluate currInput
        //String result = OUTSIDE LIBRARY FUNCTION;
        //return result;
        return null;
    }
    protected void displayResult(String value){
        //Modifies contents of current gray box in activity_main.xml to be the answer
        //Value v = getValueById(ID OF GRAY BOX);
        //v.text = value;
        currInput = value;
    }
    protected void saveToHistory(String value){
        //Stores value into history ArrayList
        history.add(new Double(value));
    }
}