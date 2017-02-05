package com.example.hunter.utsav;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class UtsavDatabaseHelper extends SQLiteOpenHelper{

    public SQLiteDatabase DB;
    public String DBPath;
    public static String DBName = "sample";
    public static final int version = '1';
    public static Context currentContext;
    public static String tableName = "Resource";

    public UtsavDatabaseHelper(Context context) {
        super(context, DBName, null, version);

        currentContext = context;

        DBPath = "/data/data/" + context.getPackageName() + "/databases";

        createDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub

    }

    private void createDatabase() {
        boolean dbExists = checkDbExists();

        if (dbExists) {
// do nothing

        } else {
            DB = currentContext.openOrCreateDatabase(DBName, 0, null);
            DB.execSQL("CREATE TABLE IF NOT EXISTS " +
                    tableName +
                    " ( FirstName VARCHAR," +
                    " Class VARCHAR, Phone VARCHAR);");

            /*
            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values ('Rahul','D7C','9321187149');");
            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values ('DiplomaSucks','D11','932181322');");
            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values ('2Shift','ForLife','92312121');");
            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values ('Rambo','D3','92323121');");
            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values ('lambo','d13','1212');");
            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values ('qwerty','d12','91212');");
  */

        }

    }

    private boolean checkDbExists() {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DBPath + DBName;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {

// database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }


        return checkDB != null ? true : false;
    }
}
