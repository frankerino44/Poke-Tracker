package com.example.poke_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ViewDatabase extends AppCompatActivity {

    private Button previousButton;
    private Button nextButton;
    private Button deleteButton;
    private Button backButton;
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
    private TextView idTV;
    private TextView nationalNumberTV;
    private TextView nameTV;
    private TextView speciesTV;
    private TextView genderTV;
    private TextView heightTV;
    private TextView weightTV;
    private TextView levelTV;
    private TextView hpTV;
    private TextView attackTV;
    private TextView defenseTV;

    Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_database);

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

        idTV = findViewById(R.id.idTV);
        nationalNumberTV = findViewById(R.id.nationalNumberTV);
        nameTV = findViewById(R.id.nameTV);
        speciesTV = findViewById(R.id.speciesTV);
        genderTV = findViewById(R.id.genderTV);
        heightTV = findViewById(R.id.heightTV);
        weightTV = findViewById(R.id.weightTV);
        levelTV = findViewById(R.id.levelTV);
        hpTV = findViewById(R.id.hpTV);
        attackTV = findViewById(R.id.attackTV);
        defenseTV = findViewById(R.id.defenseTV);

        previousButton = findViewById(R.id.previousButton);
        nextButton = findViewById(R.id.nextButton);
        deleteButton = findViewById(R.id.deleteButton);
        backButton = findViewById(R.id.backButton);

        query();

        Log.d("PREV/NEXT", String.valueOf(mCursor.getCount()));

        previousButton.setOnClickListener(previousListener);
        nextButton.setOnClickListener(nextListener);
        deleteButton.setOnClickListener(deleteListener);
        backButton.setOnClickListener(backListener);
    }

    View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switchToMainActivity();
        }
    };

    View.OnClickListener deleteListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*String mSelectionClause = MyContentProvider.COLUMN_NATIONALNUMBER + " = ? " + " AND " +
                    MyContentProvider.COLUMN_NAME + " = ? " + " AND " +
                    MyContentProvider.COLUMN_SPECIES + " = ? " + " AND " +
                    MyContentProvider.COLUMN_GENDER + " = ? " + " AND " +
                    MyContentProvider.COLUMN_HEIGHT + " = ? " + " AND " +
                    MyContentProvider.COLUMN_WEIGHT + " = ? " + " AND " +
                    MyContentProvider.COLUMN_LEVEL + " = ? " + " AND " +
                    MyContentProvider.COLUMN_HP + " = ? " + " AND " +
                    MyContentProvider.COLUMN_ATTACK + " = ? " + " AND " +
                    MyContentProvider.COLUMN_DEFENSE + " = ? ";

            String[] mSelectionArgs = { nationalNumberTV.getText().toString(),
                    nameTV.getText().toString().trim(),
                    speciesTV.getText().toString().trim(),
                    genderTV.getText().toString().trim(),
                    heightTV.getText().toString(),
                    weightTV.getText().toString(),
                    levelTV.getText().toString(),
                    hpTV.getText().toString(),
                    defenseTV.getText().toString(),
                    attackTV.getText().toString() };*/

            String mSelectionClause = BaseColumns._ID + " = ? ";

            String[] mSelectionArgs = { idTV.getText().toString() };

            int mRowsDeleted = getContentResolver().delete(MyContentProvider.CONTENT_URI, mSelectionClause,
                    mSelectionArgs);

            clear();

            query();
        }
    };

    View.OnClickListener previousListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mCursor.getCount() > 0) {
                if (!mCursor.moveToPrevious()) {
                    mCursor.moveToLast();
                }

                setViews();
            }
        }
    };

    View.OnClickListener nextListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*if (mCursor != null) {
                if (!mCursor.moveToNext()) {
                    mCursor.moveToFirst();
                }
                setViews();
            }*/
            if (mCursor.getCount() > 0) {
                if (!mCursor.moveToNext()) {
                    mCursor.moveToFirst();
                }
                setViews();
            }
        }
    };

    private void query() {
        mCursor = getContentResolver().query(MyContentProvider.CONTENT_URI, null, null, null, null);

        if (mCursor != null) {
            if (mCursor.getCount() > 0) {
                mCursor.moveToNext();
                setViews();
            }
        }
    }
    private void setViews() {
        idTV.setText(mCursor.getString(0));
        nationalNumberTV.setText(String.valueOf(mCursor.getInt(1)));
        nameTV.setText(mCursor.getString(2));
        speciesTV.setText(mCursor.getString(3));
        genderTV.setText(mCursor.getString(4));
        heightTV.setText(String.valueOf(mCursor.getDouble(5)));
        weightTV.setText(String.valueOf(mCursor.getDouble(6)));
        levelTV.setText(String.valueOf(mCursor.getInt(7)));
        hpTV.setText(String.valueOf(mCursor.getInt(8)));
        attackTV.setText(String.valueOf(mCursor.getInt(9)));
        defenseTV.setText(String.valueOf(mCursor.getInt(10)));
    }

    private void clear() {
        idTV.setText("");
        nationalNumberTV.setText("");
        nameTV.setText("");
        speciesTV.setText("");
        genderTV.setText("");
        heightTV.setText("");
        weightTV.setText("");
        levelTV.setText("");
        hpTV.setText("");
        attackTV.setText("");
        defenseTV.setText("");
    }

    private void switchToMainActivity() {
        Intent switchToMainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchToMainActivityIntent);
    }
}