package com.apcom.addressbookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.w3c.dom.Comment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apcom on 20.10.2015.
 */
public class ContactsDataSource {
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumns = {dbHelper.USER_ID, dbHelper.USER_FIRST_NAME, dbHelper.USER_LAST_NAME, dbHelper.USER_PHONE};

    public ContactsDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Profile createProfile(String firstName, String lastName, String phone) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.USER_FIRST_NAME, firstName);
        values.put(DBHelper.USER_LAST_NAME, lastName);
        values.put(DBHelper.USER_PHONE, phone);
        long insertId = database.insert(DBHelper.TABLE_NAME, null, values);

        Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns, DBHelper.USER_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();

        Profile newProfile = cursorToProfile(cursor);
        cursor.close();
        return newProfile;
    }

    public void deleteProfile(Profile profile) {
        long id = profile.getId();
        System.out.println("Profile #" + id + " deleted");
        database.delete(DBHelper.TABLE_NAME, DBHelper.USER_ID + " = " + id, null);
    }

    public List<Profile> getAllProfiles() {
        List<Profile> profiles = new ArrayList<Profile>();
        Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            Profile profile = cursorToProfile(cursor);
            profiles.add(profile);
            cursor.moveToNext();
        }
        cursor.close();
        return profiles;
    }

    public Profile cursorToProfile(Cursor cursor) {
        Profile profile = new Profile();
        profile.setId(cursor.getLong(0));
        profile.setFirst_name(cursor.getString(1));
        profile.setLast_name(cursor.getString(2));
        profile.setPhone(cursor.getString(3));
        return profile;
    }
}
