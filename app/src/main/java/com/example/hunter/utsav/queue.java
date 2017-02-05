package com.example.hunter.utsav;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class queue extends ListActivity {
    private SQLiteDatabase db;
    private Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setContentView(R.layout.activity_queue);
        ListView listGuy=getListView();

        try{
            SQLiteOpenHelper utsavDatabaseHelper=new UtsavDatabaseHelper(this);

            db=utsavDatabaseHelper.getReadableDatabase();

            cursor=db.query("GUY",new String[]{"id","NAME"},null,null,null,null,null);

            CursorAdapter listAdapter=new SimpleCursorAdapter
                    (this,android.R.layout.simple_list_item_1,cursor,new String[]{"NAME"},new int[]{android.R.id.text1},0);
            listGuy.setAdapter(listAdapter);
        }
        catch(SQLiteException e)
        {
            Toast toast=Toast.makeText(this,"Database unavailable",Toast.LENGTH_LONG);
            toast.show();

        }

    }


    @Override
    public void onDestroy()
    {
        super.onDestroy();
        cursor.close();
        db.close();
    }


}