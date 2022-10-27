package com.example.calculatorjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView previousCalculationView;
    private EditText editView;
    private EditText inputOne;
    private EditText inputTwo;
    private EditText inputThree;
    private EditText inputFour;
    private Spinner spinner;

    private String selectedOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        editView = findViewById(R.id.editView);
        inputOne = findViewById(R.id.inputOne);
        inputTwo = findViewById(R.id.inputTwo);
        inputThree = findViewById(R.id.inputThree);
        inputFour = findViewById(R.id.inputFour);

        findViewById(R.id.button).setOnClickListener((v) -> {
            calculate();
        });

        initSpinner();
    }

    private void initSpinner() {
        String[] options = {
                "+",
                "-"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                selectedOption = options[pos];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){
            }
        });

        spinner.setAdapter(adapter);
    }

    private void calculate() {
        String tempShow = "";

        if(selectedOption.equals("+")) {
            double var1 = Double.parseDouble(inputOne.getText().toString()) + Double.parseDouble(inputThree.getText().toString());
            double var2 = Double.parseDouble(inputTwo.getText().toString()) + Double.parseDouble(inputFour.getText().toString());

            String temp1 = String.valueOf(var1);
            String temp2 = String.valueOf(var2) + "i";

            tempShow = temp1 + " + " + temp2;
        } else {
            double var1 = Double.parseDouble(inputOne.getText().toString()) - Double.parseDouble(inputThree.getText().toString());
            double var2 = Double.parseDouble(inputTwo.getText().toString()) - Double.parseDouble(inputFour.getText().toString());

            String temp1 = String.valueOf(var1);
            String temp2 = String.valueOf(var2) + "i";

            tempShow = temp1 + " + " + temp2;
        }

        editView.setText(tempShow);
    }

}