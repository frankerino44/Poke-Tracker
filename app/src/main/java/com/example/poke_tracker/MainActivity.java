package com.example.poke_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.RadioGroup;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private Button resetButton;
    private Button saveButton;
    private Button viewButton;
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
    Cursor nCursor;

    View.OnClickListener resetListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            nationalNumberET.setText("896");
            nameET.setText("Glastrier");
            speciesET.setText("Wild Horse Pokemon");
            heightET.setText("2.2");
            weightET.setText("800.0");
            hpET.setText("0");
            attackET.setText("0");
            defenseET.setText("0");
            level.setSelection(0);
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
        }
    };

    View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean check = true;

            ContentValues mNewValues = new ContentValues();
            String genderString;
            Integer levelInteger = Integer.parseInt(level.getSelectedItem().toString());

            if (nationalNumberET.getText().toString().matches("") || !(0 <= Integer.valueOf(nationalNumberET.getText().toString()) && Integer.valueOf(nationalNumberET.getText().toString()) <= 1010)){
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
            if (hpET.getText().toString().matches("") || !(1 <= Integer.valueOf(hpET.getText().toString()) && Integer.valueOf(hpET.getText().toString()) <= 362)){
                check = false;
                hpLabel.setTextColor(Color.RED);
            }
            if (attackET.getText().toString().matches("") || !(5 <= Integer.valueOf(attackET.getText().toString()) && Integer.valueOf(attackET.getText().toString()) <= 526)){
                check = false;
                attackLabel.setTextColor(Color.RED);
            }
            if (defenseET.getText().toString().matches("") || !(5 <= Integer.valueOf(defenseET.getText().toString()) && Integer.valueOf(defenseET.getText().toString()) <= 614)){
                check = false;
                defenseLabel.setTextColor(Color.RED);
            }
            if (heightET.getText().toString().matches("") || !(0.3 <= Double.valueOf(heightET.getText().toString()) && Double.valueOf(heightET.getText().toString()) <= 19.99)){
                check = false;
                heightLabel.setTextColor(Color.RED);
            }
            if (weightET.getText().toString().matches("") || !(0.1 <= Double.valueOf(weightET.getText().toString()) && Double.valueOf(weightET.getText().toString()) <= 820)){
                check = false;
                weightLabel.setTextColor(Color.RED);
            }
            if (!check){
                Toast.makeText(MainActivity.this, "Please fix all fields with red labels!", Toast.LENGTH_SHORT).show();
                return;
            }

            nCursor = getContentResolver().query(MyContentProvider.CONTENT_URI, null, null, null, null);

            if(genderRadioGroup.getCheckedRadioButtonId() == R.id.maleRadioButton) {
                genderString = "Male";
            } else {
                genderString = "Female";
            }

            if (nCursor != null) {
                if (nCursor.getCount() > 0) {
                    for(int i = nCursor.getCount(); i > 0 ; i--) {
                        nCursor.moveToNext();

                        /*Log.d("DUPE", nationalNumberET.getText().toString());
                        Log.d("DUPE", String.valueOf(nCursor.getInt(1)));
                        Log.d("DUPE", nameET.getText().toString().trim());
                        Log.d("DUPE", nCursor.getString(2));
                        Log.d("DUPE", speciesET.getText().toString().trim());
                        Log.d("DUPE", nCursor.getString(3));
                        Log.d("DUPE", genderString);
                        Log.d("DUPE", nCursor.getString(4));
                        Log.d("DUPE", heightET.getText().toString());
                        Log.d("DUPE", String.valueOf(nCursor.getDouble(5)));
                        Log.d("DUPE", weightET.getText().toString());
                        Log.d("DUPE", String.valueOf(nCursor.getDouble(6)));
                        Log.d("DUPE", String.valueOf(levelInteger));
                        Log.d("DUPE", String.valueOf(nCursor.getInt(7)));
                        Log.d("DUPE", hpET.getText().toString());
                        Log.d("DUPE", String.valueOf(nCursor.getInt(8)));
                        Log.d("DUPE", attackET.getText().toString());
                        Log.d("DUPE", String.valueOf(nCursor.getInt(9)));
                        Log.d("DUPE", defenseET.getText().toString());
                        Log.d("DUPE", String.valueOf(nCursor.getInt(10)));*/

                        if(nationalNumberET.getText().toString().matches(String.valueOf(nCursor.getInt(1))) &&
                                nameET.getText().toString().trim().matches(nCursor.getString(2)) &&
                                speciesET.getText().toString().trim().matches(nCursor.getString(3)) &&
                                genderString.matches(nCursor.getString(4)) &&
                                Double.parseDouble(heightET.getText().toString()) == nCursor.getDouble(5) &&
                                Double.parseDouble(weightET.getText().toString()) == nCursor.getDouble(6) &&
                                levelInteger == nCursor.getInt(7) &&
                                hpET.getText().toString().matches(String.valueOf(nCursor.getInt(8))) &&
                                attackET.getText().toString().matches(String.valueOf(nCursor.getInt(9))) &&
                                defenseET.getText().toString().matches(String.valueOf(nCursor.getInt(10)))) {
                            Toast.makeText(MainActivity.this, "You've already tracked this Pokemon!", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                }
            }

            mNewValues.put(MyContentProvider.COLUMN_NATIONALNUMBER, Integer.parseInt(nationalNumberET.getText().toString()));
            mNewValues.put(MyContentProvider.COLUMN_NAME, nameET.getText().toString().trim());
            mNewValues.put(MyContentProvider.COLUMN_SPECIES, speciesET.getText().toString().trim());
            mNewValues.put(MyContentProvider.COLUMN_GENDER, genderString);
            mNewValues.put(MyContentProvider.COLUMN_HEIGHT, Double.parseDouble(heightET.getText().toString()));
            mNewValues.put(MyContentProvider.COLUMN_WEIGHT, Double.parseDouble(weightET.getText().toString()));
            mNewValues.put(MyContentProvider.COLUMN_LEVEL, levelInteger);
            mNewValues.put(MyContentProvider.COLUMN_HP, Integer.parseInt(hpET.getText().toString()));
            mNewValues.put(MyContentProvider.COLUMN_ATTACK, Integer.parseInt(attackET.getText().toString()));
            mNewValues.put(MyContentProvider.COLUMN_DEFENSE, Integer.parseInt(defenseET.getText().toString()));

            getContentResolver().insert(MyContentProvider.CONTENT_URI, mNewValues);

            nationalNumberET.setText("896");
            nameET.setText("Glastrier");
            speciesET.setText("Wild Horse Pokemon");
            heightET.setText("2.2");
            weightET.setText("800.0");
            hpET.setText("0");
            attackET.setText("0");
            defenseET.setText("0");
            level.setSelection(0);
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

            Toast.makeText(MainActivity.this, "Entry submitted successfully!", Toast.LENGTH_LONG).show();
        }
    };

    View.OnClickListener viewListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switchToViewDatabase();
        }
    };

    private void switchToViewDatabase() {
        Intent switchToViewDatatbaseIntent = new Intent(this, ViewDatabase.class);
        startActivity(switchToViewDatatbaseIntent);
    }

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
        viewButton = findViewById(R.id.viewButton);

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
        weightET.setText("800.0");
        hpET.setText("0");
        attackET.setText("0");
        defenseET.setText("0");

        resetButton.setOnClickListener(resetListener);
        saveButton.setOnClickListener(saveListener);
        viewButton.setOnClickListener(viewListener);
    }

    private void useStringResource() {
        String[] values = getResources().getStringArray(R.array.levels);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
        level.setAdapter(adapter);
    }
}