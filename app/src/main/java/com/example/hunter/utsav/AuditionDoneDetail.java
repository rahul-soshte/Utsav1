package com.example.hunter.utsav;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AuditionDoneDetail extends Activity implements View.OnClickListener {
    private String name;
    private String age;
    private String Phone;
private String x="You are up next in the RJ HUNT/15 mins of fame/Debate/Sitcom Quiz auditions,Report at the Alloted Room for the same ASAP.If you are not free right now,Call back ASAP to tell your preferred time.Ignore this message if you have already given the auditions";
    private EditText editTextName;
    private EditText editTextClass;
    private EditText editTextPhoneNo;
    private Button btnMessage;
//    private Button btnDelete;
    //private Button btnUpdate;
    private Button btnMissed;

    SQLiteDatabase newDB;

    public int Person_No;

    //Intent intent=new Intent(this,MainActivity.class);
    //startActivity(intent);
    public static final String EXTRA_PERSON = "Person_No";
    private Cursor c;
    private String tableName = UtsavDatabaseHelper.AuditionTableName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audition_done_detail);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextClass = (EditText) findViewById(R.id.editTextClass);
        editTextPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
        btnMessage = (Button) findViewById(R.id.btnMessage);
        //btnUpdate = (Button) findViewById(R.id.btnUpdate);
        //btnDelete = (Button) findViewById(R.id.btnDelete);
        btnMissed=(Button)findViewById(R.id.btnMissed);
        //btnUpdate.setOnClickListener(this);
        btnMessage.setOnClickListener(this);
       // btnDelete.setOnClickListener(this) ;
        btnMissed.setOnClickListener(this);
        Person_No = (Integer) getIntent().getExtras().get(EXTRA_PERSON);
        UtsavDatabaseHelper dbHelper = new UtsavDatabaseHelper(this.getApplicationContext());
        newDB = dbHelper.getWritableDatabase();
        c = newDB.rawQuery("SELECT FirstName, Class, Phone FROM " +
                tableName , null);

        showRecord(Person_No);



    }
    public void showRecord(int rec)
    {
        int i=0;

        if(c!=null) {
            c.moveToFirst();
            while(i!=rec)
            {
                c.moveToNext();
                i++;

            }
        }

        name=c.getString(c.getColumnIndex("FirstName"));
        age=c.getString(c.getColumnIndex("Class"));
        Phone=c.getString(c.getColumnIndex("Phone"));

        editTextName.setText(name);
        editTextClass.setText(age);
        editTextPhoneNo.setText(Phone);
    }
/*
    private void moveRecord() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want delete this person?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //int j=0;
                        //String id = editTextId.getText().toString().trim();
                        // String sql = "DELETE FROM persons WHERE FirstName=" + id + ";";
                        //db.execSQL(sql);
                       /* if(c!=null) {
                            c.moveToFirst();
                            while(j!=Person_No);
                            {
                                c.moveToNext();
                                j++;

                            }
                        }
                        */
                        /*
                        newDB.execSQL("INSERT INTO " +
                                AuditionTableName +
                                " Values ('"+name+"','"+age+"','"+Phone+"');");

                        //= "DELETE FROM persons WHERE FirstName=" + id + ";";
                        newDB.delete(tableName," FirstName = ? AND Class = ? AND Phone = ? ",new String[] {name,age,Phone});

                        //c=newDB.rawQuery()
                        Toast.makeText(getApplicationContext(), "Record Deleted", Toast.LENGTH_LONG).show();
                        //c = newDB.rawQuery(SELECT_SQL,null);
                        //startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
    */
/*
    private void moveRecord1()
    {
        newDB.execSQL("INSERT INTO " +
                AuditionTableName +
                " Values ('"+name+"','"+age+"','"+Phone+"');");

        newDB.delete(tableName," FirstName = ? AND Class = ? AND Phone = ? ",new String[] {name,age,Phone});

        //c=newDB.rawQuery()
        Toast.makeText(getApplicationContext(), "Moved to Auditions Done List", Toast.LENGTH_LONG).show();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    */

    public void onClick(View v) {

        if (v == btnMessage) {

            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.setData(Uri.parse("sms:"+Phone));
            sendIntent.putExtra("sms_body", x);
            startActivity(sendIntent);
        }
        if(v==btnMissed)
        {
            Intent sendIntent1=new Intent(Intent.ACTION_DIAL);
            startActivity(sendIntent1);


        }

    }

}