package com.example.poke_tracker;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

    public final static String DBPOKEMON = "PokemonDatabase";

    protected static final class MainDatabaseHelper extends SQLiteOpenHelper {
        MainDatabaseHelper(Context context) {
            super(context, DBPOKEMON, null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_MAIN);
        }
        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        }
    };

    public final static String TABLE_POKEMONTABLE = "Pokemon";
    public final static String COLUMN_NATIONALNUMBER = "NationalNumber";
    public final static String COLUMN_NAME = "Name";
    public final static String COLUMN_SPECIES = "Species";
    public final static String COLUMN_GENDER = "Gender";
    public final static String COLUMN_HEIGHT = "Height";
    public final static String COLUMN_WEIGHT = "Weight";
    public final static String COLUMN_LEVEL = "Level";
    public final static String COLUMN_HP = "HP";
    public final static String COLUMN_ATTACK = "Attack";
    public final static String COLUMN_DEFENSE = "Defense";

    public static final String AUTHORITY = "PokeTracker";
    public static final Uri CONTENT_URI = Uri.parse(
            "content://" + AUTHORITY +"/" + TABLE_POKEMONTABLE);

    private static UriMatcher sUriMatcher;

    private MainDatabaseHelper mOpenHelper;

    private static final String SQL_CREATE_MAIN = "CREATE TABLE " +
            TABLE_POKEMONTABLE +  // Table's name
            "(" +               // The columns in the table
            " _ID INTEGER PRIMARY KEY, " +
            COLUMN_NATIONALNUMBER +
            " SMALLINT," +
            COLUMN_NAME +
            " TEXT," +
            COLUMN_SPECIES +
            " TEXT," +
            COLUMN_GENDER +
            " TEXT," +
            COLUMN_HEIGHT +
            " DOUBLE," +
            COLUMN_WEIGHT +
            " DOUBLE," +
            COLUMN_LEVEL +
            " SMALLINT," +
            COLUMN_HP +
            " SMALLINT," +
            COLUMN_ATTACK +
            " SMALLINT," +
            COLUMN_DEFENSE +
            " SMALLINT)";

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        return mOpenHelper.getWritableDatabase().delete(TABLE_POKEMONTABLE, selection, selectionArgs);
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        Integer nationalNumber = values.getAsInteger(COLUMN_NATIONALNUMBER);
        String name = values.getAsString(COLUMN_NAME).trim();
        String species = values.getAsString(COLUMN_SPECIES).trim();
        String gender = values.getAsString(COLUMN_GENDER);
        Double height = values.getAsDouble(COLUMN_HEIGHT);
        Double weight = values.getAsDouble(COLUMN_WEIGHT);
        Integer level = values.getAsInteger(COLUMN_LEVEL);
        Integer hp = values.getAsInteger(COLUMN_HP);
        Integer attack = values.getAsInteger(COLUMN_ATTACK);
        Integer defense = values.getAsInteger(COLUMN_DEFENSE);

        if (nationalNumber.equals(null))
            return null;

        if (name.equals(null))
            return null;

        if (species.equals(null))
            return null;

        if (gender.equals(null))
            return null;

        if (height.equals(null))
            return null;

        if (weight.equals(null))
            return null;

        if (level.equals(null))
            return null;

        if (hp.equals(null))
            return null;

        if (attack.equals(null))
            return null;

        if (defense.equals(null))
            return null;

        long id = mOpenHelper.getWritableDatabase().insert(TABLE_POKEMONTABLE, null, values);

        return Uri.withAppendedPath(CONTENT_URI, "" + id);
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        mOpenHelper = new MainDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        return mOpenHelper.getReadableDatabase().query(TABLE_POKEMONTABLE, projection, selection, selectionArgs,
                null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}