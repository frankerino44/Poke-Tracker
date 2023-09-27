package com.example.poke_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.RadioGroup;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button resetButton;
    private Button saveButton;
    private EditText nationalNumberET;
    private EditText nameET;
    private EditText speciesET;
    private RadioGroup genderRadioGroup;
    private EditText heightET;
    private EditText weightET;
    private Spinner level;
    private EditText hpET;
    private EditText attackET;
    private EditText defenseET;
    private TextView nationalNumberLabel;
    private TextView nameLabel;
    private TextView speciesLabel;
    private TextView genderLabel;
    private TextView heightLabel;
    private TextView weightLabel;
    private TextView levelLabel;
    private TextView hpLabel;
    private TextView attackLabel;
    private TextView defenseLabel;

    View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == findViewById(R.id.resetButton)){
                nationalNumberET.setText("896");
                nameET.setText("Glastrier");
                speciesET.setText("Wild Horse Pokemon");
                heightET.setText("2.2");
                weightET.setText("800");
                hpET.setText("0");
                attackET.setText("0");
                defenseET.setText("0");
                genderRadioGroup.clearCheck();

                nationalNumberLabel.setTextColor(Color.BLACK);
                nameLabel.setTextColor(Color.BLACK);
                speciesLabel.setTextColor(Color.BLACK);
                genderLabel.setTextColor(Color.BLACK);
                heightLabel.setTextColor(Color.BLACK);
                weightLabel.setTextColor(Color.BLACK);
                levelLabel.setTextColor(Color.BLACK);
                hpLabel.setTextColor(Color.BLACK);
                attackLabel.setTextColor(Color.BLACK);
                defenseLabel.setTextColor(Color.BLACK);

                Toast.makeText(MainActivity.this, "All fields successfully reset!", Toast.LENGTH_SHORT).show();
            }else{
                boolean check = true;
                if (nationalNumberET.getText().toString().matches("") || !(0 <= Float.valueOf(nationalNumberET.getText().toString()) && Float.valueOf(nationalNumberET.getText().toString()) <= 1010)){
                    check = false;
                    nationalNumberLabel.setTextColor(Color.RED);
                }
                if (nameET.getText().toString().matches("") || !(3 <= nameET.getText().toString().length() && nameET.getText().toString().length() <= 12)){
                    check = false;
                    nameLabel.setTextColor(Color.RED);
                }
                if (genderRadioGroup.getCheckedRadioButtonId() == -1){
                    check = false;
                    genderLabel.setTextColor(Color.RED);
                }
                if (hpET.getText().toString().matches("") || !(1 <= Float.valueOf(hpET.getText().toString()) && Float.valueOf(hpET.getText().toString()) <= 362)){
                    check = false;
                    hpLabel.setTextColor(Color.RED);
                }
                if (attackET.getText().toString().matches("") || !(5 <= Float.valueOf(attackET.getText().toString()) && Float.valueOf(attackET.getText().toString()) <= 526)){
                    check = false;
                    attackLabel.setTextColor(Color.RED);
                }
                if (defenseET.getText().toString().matches("") || !(5 <= Float.valueOf(defenseET.getText().toString()) && Float.valueOf(defenseET.getText().toString()) <= 614)){
                    check = false;
                    defenseLabel.setTextColor(Color.RED);
                }
                if (heightET.getText().toString().matches("") || !(0.3 <= Float.valueOf(heightET.getText().toString()) && Float.valueOf(heightET.getText().toString()) <= 19.99)){
                    check = false;
                    heightLabel.setTextColor(Color.RED);
                }
                if (weightET.getText().toString().matches("") || !(0.1 <= Float.valueOf(weightET.getText().toString()) && Float.valueOf(weightET.getText().toString()) <= 820)){
                    check = false;
                    weightLabel.setTextColor(Color.RED);
                }
                if (!check){
                    Toast.makeText(MainActivity.this, "Please fix all fields with red labels!", Toast.LENGTH_SHORT).show();
                }else{
                    nationalNumberLabel.setTextColor(Color.BLACK);
                    nameLabel.setTextColor(Color.BLACK);
                    speciesLabel.setTextColor(Color.BLACK);
                    genderLabel.setTextColor(Color.BLACK);
                    heightLabel.setTextColor(Color.BLACK);
                    weightLabel.setTextColor(Color.BLACK);
                    levelLabel.setTextColor(Color.BLACK);
                    hpLabel.setTextColor(Color.BLACK);
                    attackLabel.setTextColor(Color.BLACK);
                    defenseLabel.setTextColor(Color.BLACK);

                    Toast.makeText(MainActivity.this, "Entry submitted successfully!", Toast.LENGTH_LONG).show();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraint);

        level = findViewById(R.id.levelSpinner);
        nationalNumberET = findViewById(R.id.nationalNumberET);
        nameET = findViewById(R.id.nameET);
        speciesET = findViewById(R.id.speciesET);
        heightET = findViewById(R.id.heightET);
        weightET = findViewById(R.id.weightET);
        hpET = findViewById(R.id.hpET);
        attackET = findViewById(R.id.attackET);
        defenseET = findViewById(R.id.defenseET);
        resetButton = findViewById(R.id.resetButton);
        saveButton = findViewById(R.id.saveButton);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);

        levelLabel = findViewById(R.id.levelLabel);
        nationalNumberLabel = findViewById(R.id.nationalNumberLabel);
        nameLabel = findViewById(R.id.nameLabel);
        speciesLabel = findViewById(R.id.speciesLabel);
        heightLabel = findViewById(R.id.heightLabel);
        weightLabel = findViewById(R.id.weightLabel);
        hpLabel = findViewById(R.id.hpLabel);
        attackLabel = findViewById(R.id.attackLabel);
        defenseLabel = findViewById(R.id.defenseLabel);
        genderLabel = findViewById(R.id.genderLabel);

        nationalNumberET.setText("896");
        nameET.setText("Glastrier");
        speciesET.setText("Wild Horse Pokemon");
        heightET.setText("2.2");
        weightET.setText("800");
        hpET.setText("0");
        attackET.setText("0");
        defenseET.setText("0");

        resetButton.setOnClickListener(buttonClick);
        saveButton.setOnClickListener(buttonClick);

    }

    private void useStringResource() {
        String[] values = getResources().getStringArray(R.array.levels);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
        level.setAdapter(adapter);
    }
}