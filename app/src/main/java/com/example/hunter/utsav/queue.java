package com.example.hunter.utsav;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class queue extends Activity implements View.OnClickListener {

    private EditText editTextName;
    private EditText editTextClass;
    private EditText editTextPhoneNo;
    private Button btnAdd;
    private SQLiteDatabase newDB;
    private String tableName = UtsavDatabaseHelper.tableName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);


        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextClass=(EditText)findViewById(R.id.editTextClass);

        editTextPhoneNo=(EditText)findViewById(R.id.editTextPhoneNo);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
   //     createDatabase();
    }
/*
    protected void createDatabase(){/*
        db=openOrCreateDatabase("PersonDB1", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS persons2(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR,Class VARCHAR,Shift VARCHAR,PhoneNo VARCHAR,AudComp VARCHAR);");


    }
    */


    protected void insertIntoDB(){

        UtsavDatabaseHelper dbhelper=new UtsavDatabaseHelper(this.getApplicationContext());
        newDB = dbhelper.getWritableDatabase();


        String name = editTextName.getText().toString().trim();
        String Class = editTextClass.getText().toString().trim();
        String PhoneNo=editTextPhoneNo.getText().toString().trim();



        if(name.equals("") || Class.equals("") ||  PhoneNo.equals("")){
            Toast.makeText(getApplicationContext(),"Please fill all fields", Toast.LENGTH_LONG).show();
            return;
        }
/*
        String query = "INSERT INTO persons2 (name,Class,Shift,PhoneNo,AudComp) VALUES('"+name+"', '"+Class+"','"+Shift+"','"+PhoneNo+"','"+AudComp+"');";
        db.execSQL(query);

  */
        newDB.execSQL("INSERT INTO " +
                tableName +
                " Values ('"+name+"','"+Class+"','"+PhoneNo+"');");

        Toast.makeText(getApplicationContext(),"Saved Successfully", Toast.LENGTH_LONG).show();
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //Inflate the menu;this adds item to the action bar if present
        getMenuInflater().inflate(R.menu.menu_main1,menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings: {
                return true;
            }

            default:
                return super.onOptionsItemSelected(item);

        }

    }
*/


    public void onClick(View v)
    {
        if(v==btnAdd)
        {
            insertIntoDB();
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);



        }
    }


}
