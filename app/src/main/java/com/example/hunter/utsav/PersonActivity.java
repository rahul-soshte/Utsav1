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

import static com.example.hunter.utsav.UtsavDatabaseHelper.tableName;

public class PersonActivity extends Activity implements View.OnClickListener {


    private String name;
    private String age;
    private String Phone;
private String x="Scene kya hain? ....Kya hain Scene ? Apna toh sahi hain tera kya scene kya hain?";
    private EditText editTextName;
    private EditText editTextClass;
    private EditText editTextPhoneNo;
    private Button btnMessage;
    private Button btnDelete;
    private Button btnUpdate;
    private Button btnMissed;

    SQLiteDatabase newDB;

    public static final String EXTRA_PERSON = "Person_No";
private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextClass = (EditText) findViewById(R.id.editTextClass);
        editTextPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
        btnMessage = (Button) findViewById(R.id.btnMessage);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnMissed=(Button)findViewById(R.id.btnMissed);
        btnUpdate.setOnClickListener(this);
        btnMessage.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        int Person_No = (Integer) getIntent().getExtras().get(EXTRA_PERSON);
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


    public void onClick(View v) {
        if (v == btnUpdate) {
        }
        if (v == btnDelete) {

        }
        if (v == btnMessage) {
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.setData(Uri.parse("sms:"+Phone));
            sendIntent.putExtra("sms_body", x);
            startActivity(sendIntent);
        }
        if(v==btnMissed)
        {

        }
    }

}