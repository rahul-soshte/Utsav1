package com.example.hunter.utsav;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

    public ArrayList<String> results = new ArrayList<String>();
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
      /*  TextView tView = new TextView(this);
        tView.setText("This is a Audition Queue Scheduling App.This is where the particpants are added using the Add People Option above!Also besides it is the Auditions Done List Options");
        getListView().addHeaderView(tView);
        */
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, results));
        getListView().setTextFilterEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //Inflate the menu;this adds item to the action bar if present
        getMenuInflater().inflate(R.menu.menu_main1,menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_new_person:
                //Code to run when the New Person item is clicked
                Intent intent=new Intent(this,queue.class);
                startActivity(intent);
    //          c.moveToFirst();

                return true;
            case R.id.audition_done:
                Intent intent1=new Intent(this,AuditionsDoneList.class);
                startActivity(intent1);

            default:return  super.onOptionsItemSelected(item);

        }
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

        } catch (SQLiteException e ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } /*finally {
            if (newDB != null)
                newDB.execSQL("DELETE FROM " + tableName);
            newDB.close();
        }*/


    }
    @Override
    public void onListItemClick (ListView listView, View itemView, int position, long id)
    {
        Intent intent = new Intent(MainActivity.this, PersonActivity.class);
        intent.putExtra(PersonActivity.EXTRA_PERSON,(int)id);
        startActivity(intent);
    }

}