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
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


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
        System.out.println(symbol);
        if(symbol.equals("=")){
            //Call evaluate(), store result in string
            String result = evaluate();
            //Call displayResult(result), which displays result in activity_main


            //displayResult(result);


            //Call saveToHistory(result), which passes result to the history ArrayList
            //saveToHistory(result);
            System.out.println(result);
        }
        else if(symbol.equals("Ans")){
            //Obtains previous answer from history ArrayList if history is not empty
            if(!history.isEmpty()){
                String prevAns = history.get(history.size() - 1).toString();
                currInput += prevAns;
            }
        }
        else if(symbol.equals("C")){
            // clear currInput
            currInput = "";
        }
        else if (symbol.equals("del")){
            // delete last character from currInput
            if (currInput.length() != 0) {
                currInput = currInput.substring(0, currInput.length() - 1);
            }
        }
        else{
            currInput += symbol;
        }
    }

    // returns null if invalid expression
    // returns double if valid expression
    protected String evaluate(){
        //Call outside library to evaluate currInput
        //String result = OUTSIDE LIBRARY FUNCTION;
        //return result;

        // edge cases
        // 1. Evaluating empty string expression
        // 2. Evaluating string expression that ends with operator

        String exprStr = currInput;

        // edge case checks
        if (exprStr.length() == 0){
            return null;
        }
        String lastChar = exprStr.substring(exprStr.length() - 1);
        if (lastChar.equals("/") || lastChar.equals("*") || lastChar.equals("-") || lastChar.equals("+")){
            return null;
        }

        // Build and evaluate expression
        Expression expr = new ExpressionBuilder(exprStr).build();
        double result = expr.evaluate();

        return String.valueOf(result);
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