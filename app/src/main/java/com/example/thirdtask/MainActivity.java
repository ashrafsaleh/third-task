package com.example.thirdtask;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView result;
    EditText num1;
    Button sum, mul, div, sub, undo, redo, equal;
    String operator_clicked;
    GridView gridView;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1 = (EditText) findViewById(R.id.num1);
        result = (TextView) findViewById(R.id.result);
        sum = (Button) findViewById(R.id.sum);
        mul = (Button) findViewById(R.id.mul);
        div = (Button) findViewById(R.id.div);
        sub = (Button) findViewById(R.id.sub);
        undo = (Button) findViewById(R.id.undo);
        redo = (Button) findViewById(R.id.redo);
        equal = (Button) findViewById(R.id.equal);
        gridView = findViewById(R.id.grid);

        final ArrayList<String> history_list=new ArrayList<>();
        final MyAdapter adapter=new MyAdapter(this,history_list);
        gridView.setAdapter(adapter);

        final ArrayList<Float> undo_numbers = new ArrayList<>();
        final ArrayList<String> undo_operator = new ArrayList<>();
        final ArrayList<Float> redo_numbers = new ArrayList<>();
        final ArrayList<String> redo_operator = new ArrayList<>();

        equal.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")

            @Override
            public void onClick(View v) {
                if (operator_clicked != null) {

                    float f = 0;
                    float number1 = Float.parseFloat(result.getText().toString());
                    float number2 = Float.parseFloat(num1.getText().toString());
                    if(num1.getText().toString().isEmpty()){
                        Toast.makeText(MainActivity.this, "please enter number first", Toast.LENGTH_SHORT).show();
                    }

                    if (operator_clicked.equals("+")){
                        f = number1 + number2;
                    }
                    else if (operator_clicked.equals("-")){
                        f = number1 - number2;
                    }
                    else if (operator_clicked.equals("*")){
                        f = number1 * number2;}
                    else if (operator_clicked.equals("/")){
                        if (number2 == 0 ){
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                        f = number1 / number2;
                    }

                    result.setText(f + "");
                    num1.setText("");
                    undo_numbers.add(number2);
                    undo_operator.add(operator_clicked);

                    history_list.add(operator_clicked+number2);
                    adapter.notifyDataSetChanged();

                    operator_clicked = null;
                } else
                    Toast.makeText(MainActivity.this, "choose an operator", Toast.LENGTH_SHORT).show();
            }
        });
        sum.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                operator_clicked = "+";
                equal.setVisibility(View.VISIBLE);
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                operator_clicked = "*";
                equal.setVisibility(View.VISIBLE);
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                operator_clicked = "/";
                equal.setVisibility(View.VISIBLE);
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                operator_clicked = "-";
                equal.setVisibility(View.VISIBLE);
            }
        });
        redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = redo_numbers.size() - 1;
                if (index != -1) {
                    float number = redo_numbers.get(index);
                    String operator = redo_operator.get(index);
                    float res = Float.parseFloat(result.getText().toString());

                    if (operator.equals("+"))
                        result.setText(res + number + "");
                    else if (operator.equals("-"))
                        result.setText(res - number + "");
                    else if (operator.equals("*"))
                        result.setText(res * number + "");
                    else if (operator.equals("/"))
                        result.setText(res / number + "");

                    redo_numbers.remove(index);
                    redo_operator.remove(index);

                } else
                    Toast.makeText(MainActivity.this, "no operations to redo", Toast.LENGTH_SHORT).show();

            }
        });
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = undo_numbers.size() - 1;
                if (index != -1) {
                    float number = undo_numbers.get(index);
                    String operator = undo_operator.get(index);
                    float res = Float.parseFloat(result.getText().toString());

                    if (operator.equals("+"))
                        result.setText(res - number + "");
                    else if (operator.equals("-"))
                        result.setText(res + number + "");
                    else if (operator.equals("*"))
                        result.setText(res / number + "");
                    else if (operator.equals("/"))
                        result.setText(res * number + "");

                    undo_numbers.remove(index);
                    undo_operator.remove(index);

                    redo_numbers.add(number);
                    redo_operator.add(operator);

                } else
                    Toast.makeText(MainActivity.this, "no operations to undo", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
