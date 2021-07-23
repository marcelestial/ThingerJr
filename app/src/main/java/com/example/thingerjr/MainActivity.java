
package com.example.thingerjr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    
    double nut1PerG, nut2PerG, nut3PerG, nut4PerG, nut5PerG;
    EditText gramBox, nutBox1, nutBox2, nutBox3, nutBox4, nutBox5;
    TextWatcher watcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //instantiate text fields
        gramBox = (EditText) findViewById(R.id.grams_editTextNumberDecimal);
        nutBox1 = (EditText) findViewById(R.id.nut1_editTextNumberDecimal);
        nutBox2 = (EditText) findViewById(R.id.nut2_editTextNumberDecimal);
        nutBox3 = (EditText) findViewById(R.id.nut3_editTextNumberDecimal);
        nutBox4 = (EditText) findViewById(R.id.nut4_editTextNumberDecimal);
        nutBox5 = (EditText) findViewById(R.id.nut5_editTextNumberDecimal);


        //lock nutrient fields if grams are zero
        if (gramBox.getText().toString().isEmpty() || Double.parseDouble(gramBox.getText().toString()) == 0){
            nutBox1.setFocusable(false);
            nutBox2.setFocusable(false);
            nutBox3.setFocusable(false);
            nutBox4.setFocusable(false);
            nutBox5.setFocusable(false);
        }

        //assign a textwatcher to all the fields
        watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                update(s);
            }
        };

        gramBox.addTextChangedListener(watcher);
        nutBox1.addTextChangedListener(watcher);
        nutBox2.addTextChangedListener(watcher);
        nutBox3.addTextChangedListener(watcher);
        nutBox4.addTextChangedListener(watcher);
        nutBox5.addTextChangedListener(watcher);
    }
    
    public void update(Editable s){
        if (!gramBox.getText().toString().isEmpty() && Double.parseDouble(gramBox.getText().toString()) != 0) {
            double grams = Double.parseDouble(gramBox.getText().toString());

            //if the text changed was the grams, adjust all displayed text accordingly
            if (s == gramBox.getEditableText()) {
                if (!nutBox1.getText().toString().isEmpty()) {
                    nutBox1.setText("" + round(nut1PerG * grams, 1));
                }
                if (!nutBox2.getText().toString().isEmpty()) {
                    nutBox2.setText("" + round(nut2PerG * grams, 1));
                }
                if (!nutBox3.getText().toString().isEmpty()) {
                    nutBox3.setText("" + round(nut3PerG * grams, 1));
                }
                if (!nutBox4.getText().toString().isEmpty()) {
                    nutBox4.setText("" + round(nut4PerG * grams, 1));
                }
                if (!nutBox5.getText().toString().isEmpty()) {
                    nutBox5.setText("" + round(nut5PerG * grams, 1));
                }


                if (grams != 0) {
                    nutBox1.setFocusableInTouchMode(true);
                    nutBox2.setFocusableInTouchMode(true);
                    nutBox3.setFocusableInTouchMode(true);
                    nutBox4.setFocusableInTouchMode(true);
                    nutBox5.setFocusableInTouchMode(true);
                }
            }
            //if text changed was not the grams, update a nutrient-per-gram value
            else {
                if (s == nutBox1.getEditableText() && !nutBox1.getText().toString().isEmpty()) {
                    double nut1 = Double.parseDouble(nutBox1.getText().toString());
                    nut1PerG = nut1 / grams;
                }
                if (s == nutBox2.getEditableText() && !nutBox2.getText().toString().isEmpty()) {
                    double nut2 = Double.parseDouble(nutBox2.getText().toString());
                    nut2PerG = nut2 / grams;
                }
                if (s == nutBox3.getEditableText() && !nutBox3.getText().toString().isEmpty()) {
                    double nut3 = Double.parseDouble(nutBox3.getText().toString());
                    nut3PerG = nut3 / grams;
                }
                if (s == nutBox4.getEditableText() && !nutBox4.getText().toString().isEmpty()) {
                    double nut4 = Double.parseDouble(nutBox4.getText().toString());
                    nut4PerG = nut4 / grams;
                }
                if (s == nutBox5.getEditableText() && !nutBox5.getText().toString().isEmpty()) {
                    double nut5 = Double.parseDouble(nutBox5.getText().toString());
                    nut5PerG = nut5 / grams;
                }
            }
        }
    }

    private double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    public void clearFields(android.view.View view){
        gramBox.setText("");
        nutBox1.setText("");
        nutBox2.setText("");
        nutBox3.setText("");
        nutBox4.setText("");
        nutBox5.setText("");

        nut1PerG = 0;
        nut2PerG = 0;
        nut3PerG = 0;
        nut4PerG = 0;
        nut5PerG = 0;

        nutBox1.setFocusable(false);
        nutBox2.setFocusable(false);
        nutBox3.setFocusable(false);
        nutBox4.setFocusable(false);
        nutBox5.setFocusable(false);
    }
}