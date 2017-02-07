package com.example.hunter.utsav;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PersonActivity extends Activity implements View.OnClickListener {


    private EditText editTextName;
    private EditText editTextClass;
    private EditText editTextPhoneNo;
    private Button btnMessage;
    private Button btnDelete;
    private Button btnUpdate;
    public static final String EXTRA_PERSON = "Person_No";

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

        btnUpdate.setOnClickListener(this);
        btnMessage.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

    }

    public void onClick(View v) {
        if (v == btnUpdate) {

        }
        if (v == btnDelete) {

        }
        if (v == btnMessage) {

        }
    }

}