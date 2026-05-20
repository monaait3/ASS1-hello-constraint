package com.example.simplecalc;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView textViewData = findViewById(R.id.textView_data);

        // استقبال البيانات من Intent
        String num1 = getIntent().getStringExtra("NUM1");
        String num2 = getIntent().getStringExtra("NUM2");
        String result = getIntent().getStringExtra("RESULT");
        String operation = getIntent().getStringExtra("OPERATION");

        // عرض البيانات
        String display = "Number 1: " + num1 + "\n" +
                "Number 2: " + num2 + "\n" +
                "Operation: " + operation + "\n" +
                "Result: " + result;

        textViewData.setText(display);
    }
}