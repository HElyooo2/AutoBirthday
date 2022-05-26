package com.example.autobirthday;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.time.LocalDateTime;
import java.util.Date;

public class DBHandler extends SQLiteOpenHelper {



    private static final String DB_NAME = "auto_birthday";

    private static final int DB_VERSION = 1;


    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        add_profile_button



        db.execSQL("CREATE TABLE Message(\n" +
                "\tnum        INTEGER PRIMARY KEY AUTOINCREMENT ,\n" +
                "\tmessage    TEXT NOT NULL,\n" +
                "\tname    TEXT NOT NULL\n "+
                ");");

        db.execSQL("CREATE TABLE Profile(\n" +
                "\tid             INTEGER PRIMARY KEY AUTOINCREMENT ,\n" +
                "\tname           TEXT NOT NULL ,\n" +
                "\tphoneNumber    TEXT NOT NULL ,\n" +
                "\tdate           DATE NOT NULL \n" +

                ");");

        db.execSQL("CREATE TABLE Messages(\n" +
                "\tnum    INTEGER NOT NULL ,\n" +
                "\tid     INTEGER NOT NULL,\n" +
                "\tCONSTRAINT Messages_PK PRIMARY KEY (num,id)\n" +
                "\n" +
                "\t,CONSTRAINT Messages_Message_FK FOREIGN KEY (num) REFERENCES Message(num)\n" +
                "\t,CONSTRAINT Messages_Profile0_FK FOREIGN KEY (id) REFERENCES Profile(id)\n" +
                ");");
    }

    // this method is use to add new course to our sqlite database.
    public void addProfile(String profileName, String phoneNumber, LocalDateTime date) {


        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        add_profile_button
        values.put("name", profileName);
        values.put("phoneNumber", phoneNumber);
        values.put("date", String.valueOf(date));



        db.insert("Profile", null, values);

        db.close();
        Log.d("Test", "addProfile: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        add_profile_button
        db.execSQL("DROP TABLE IF EXISTS " + "Profile"+"Message"+"Messages");


        onCreate(db);
    }
}
