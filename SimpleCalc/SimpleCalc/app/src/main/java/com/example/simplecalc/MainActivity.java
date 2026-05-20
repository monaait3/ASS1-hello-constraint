package com.example.simplecalc;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNum1, editTextNum2;
    private TextView textViewResult;
    private String currentOperation = "";
    private double currentResult = 0.0;
    private int lastColor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNum1 = findViewById(R.id.editText_num1);
        editTextNum2 = findViewById(R.id.editText_num2);
        textViewResult = findViewById(R.id.textView_result);

        Button buttonAdd = findViewById(R.id.button_add);
        Button buttonSub = findViewById(R.id.button_sub);
        Button buttonDiv = findViewById(R.id.button_div);
        Button buttonMul = findViewById(R.id.button_mul);

        buttonAdd.setOnClickListener(v -> performCalculation("ADD"));
        buttonSub.setOnClickListener(v -> performCalculation("SUB"));
        buttonDiv.setOnClickListener(v -> performCalculation("DIV"));
        buttonMul.setOnClickListener(v -> performCalculation("MUL"));

        textViewResult.setOnClickListener(v -> openResultActivity());
    }

    private void performCalculation(String operation) {
        String num1Str = editTextNum1.getText().toString();
        String num2Str = editTextNum2.getText().toString();

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            textViewResult.setText(R.string.error_empty);
            return;
        }

        double num1 = Double.parseDouble(num1Str);
        double num2 = Double.parseDouble(num2Str);
        double result = 0;

        switch (operation) {
            case "ADD":
                result = num1 + num2;
                break;
            case "SUB":
                result = num1 - num2;
                break;
            case "DIV":
                if (num2 == 0) {
                    textViewResult.setText(R.string.error_zero);
                    return;
                }
                result = num1 / num2;
                break;
            case "MUL":
                result = num1 * num2;
                break;
        }

        currentResult = result;
        currentOperation = operation;
        textViewResult.setText(String.valueOf(result));

        int color = android.graphics.Color.BLACK;
        switch (operation) {
            case "ADD":
                color = android.graphics.Color.parseColor("#F44336");
                break;
            case "SUB":
                color = android.graphics.Color.parseColor("#4CAF50");
                break;
            case "DIV":
                color = android.graphics.Color.parseColor("#2196F3");
                break;
            case "MUL":
                color = android.graphics.Color.parseColor("#FF9800");
                break;
        }
        textViewResult.setTextColor(color);
        lastColor = color;
    }

    private void openResultActivity() {
        String num1 = editTextNum1.getText().toString();
        String num2 = editTextNum2.getText().toString();
        String result = textViewResult.getText().toString();

        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("NUM1", num1);
        intent.putExtra("NUM2", num2);
        intent.putExtra("RESULT", result);
        intent.putExtra("OPERATION", currentOperation);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("num1", editTextNum1.getText().toString());
        outState.putString("num2", editTextNum2.getText().toString());
        outState.putDouble("result", currentResult);
        outState.putString("operation", currentOperation);
        outState.putInt("color", lastColor);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        editTextNum1.setText(savedInstanceState.getString("num1", ""));
        editTextNum2.setText(savedInstanceState.getString("num2", ""));
        currentResult = savedInstanceState.getDouble("result", 0.0);
        currentOperation = savedInstanceState.getString("operation", "");
        lastColor = savedInstanceState.getInt("color", 0);
        textViewResult.setText(String.valueOf(currentResult));
        if (lastColor != 0) {
            textViewResult.setTextColor(lastColor);
        }
    }
}