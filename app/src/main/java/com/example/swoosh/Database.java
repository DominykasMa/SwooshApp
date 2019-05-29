package com.example.swoosh;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "highscore.db";

    private static String SCORES_TABLE_NAME = "scores";

    private final static String KEY_ID = "id";
    private final static String KEY_NAME = "name";
    private final static String KEY_SCORE = "score";


    public Database(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + SCORES_TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_NAME + " TEXT," +
                KEY_SCORE + " INTEGER" +
                ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String query = "DROP TABLE IF EXISTS " + SCORES_TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public long addEntry(DatabaseEntry entry)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(KEY_NAME, entry.getName());
        cv.put(KEY_SCORE, entry.getScore());

        long id = db.insert(SCORES_TABLE_NAME, null, cv);
        db.close();
        return id;
    }

    public DatabaseEntry getEntry(int id)
    {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = null;

        cursor = db.query(SCORES_TABLE_NAME, new String[] { KEY_ID, KEY_NAME, KEY_SCORE}, KEY_ID + "=?", new String[] { Integer.toString(id) }, null, null, null);

        DatabaseEntry entry = new DatabaseEntry();
        if(cursor != null)
        {
            if(cursor.moveToFirst())
            {
                entry.setID(cursor.getInt(0));
                entry.setName(cursor.getString(1));
                entry.setScore(cursor.getInt(2));
            }
        }

        cursor.close();
        db.close();

        return entry;
    }

    public ArrayList<DatabaseEntry> getAllEntries()
    {
        ArrayList<DatabaseEntry> entries = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM " + SCORES_TABLE_NAME + " ORDER BY " + KEY_SCORE + " DESC";

        Cursor cursor = db.rawQuery(query, null);

        if(cursor != null)
        {
            if(cursor.moveToFirst())
            {
                do {
                    DatabaseEntry entry = new DatabaseEntry();
                    entry.setID(cursor.getInt(0));
                    entry.setName(cursor.getString(1));
                    entry.setScore(cursor.getInt(2));
                    entries.add(entry);
                } while(cursor.moveToNext());
            }
        }

        cursor.close();
        db.close();

        return entries;
    }

    public void deleteEntry(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(SCORES_TABLE_NAME, KEY_ID + "=?", new String[]{Integer.toString(id)});
        db.close();
    }

    public void updateEntry(DatabaseEntry entry)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(KEY_NAME, entry.getName());
        cv.put(KEY_SCORE, entry.getScore());

        db.update(SCORES_TABLE_NAME, cv, KEY_ID + "=?", new String[] { Integer.toString(entry.getID()) });

        db.close();
    }
}
