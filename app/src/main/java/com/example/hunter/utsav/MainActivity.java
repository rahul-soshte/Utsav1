package com.example.hunter.utsav;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends ListActivity {

    private ArrayList<String> results = new ArrayList<String>();
    private String tableName = UtsavDatabaseHelper.tableName;
    private SQLiteDatabase newDB;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openAndQueryDatabase();

        displayResultList();

    }
    private void displayResultList() {
        TextView tView = new TextView(this);
        tView.setText("This is a precreated database not a dynamically added one");
        getListView().addHeaderView(tView);

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, results));
        getListView().setTextFilterEnabled(true);

    }
    private void openAndQueryDatabase() {
        try {
            UtsavDatabaseHelper dbHelper = new UtsavDatabaseHelper(this.getApplicationContext());
            newDB = dbHelper.getWritableDatabase();
            Cursor c = newDB.rawQuery("SELECT FirstName, Class FROM " +
                    tableName , null);


            if (c != null ) {
                if (c.moveToFirst()) {
                    do {
                        String firstName = c.getString(c.getColumnIndex("FirstName"));
                        String age = c.getString(c.getColumnIndex("Class"));
                        results.add("Name: " + firstName + " Class: " + age);
                    }while (c.moveToNext());
                }
            }
        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
            if (newDB != null)
                newDB.execSQL("DELETE FROM " + tableName);
            newDB.close();
        }

    }

}