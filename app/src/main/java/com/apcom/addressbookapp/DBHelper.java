package com.apcom.addressbookapp;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by apcom on 19.10.2015.
 */
public class DBHelper extends SQLiteOpenHelper implements BaseColumns {

    public static final String DATABASE_NAME = "contacts";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "contacts";

    /* Столбцы в таблице */
    public static final String USER_ID = "_id";
    public static final String USER_FIRST_NAME = "first_name";
    public static final String USER_LAST_NAME = "last_name";
    public static final String USER_PHONE = "phone";

    /* SQL-запросы */
    //Создание таблицы
    private static final String TABLE_CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " ("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + USER_FIRST_NAME + " TEXT NOT NULL, "
            + USER_LAST_NAME + " TEXT NOT NULL, "
            + USER_PHONE + " TEXT NOT NULL);";
    //Удаление таблицы
    private static final String DROP_TABLE_QUERY = "DROP TABLE IF IT EXISTS " + TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(), "upgrading database from v" + oldVersion + " to v" + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /* --- */

    /*public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("SQLite", "Обновляем версию базы данных на v" + newVersion);

        //Удаляем старую таблицу и создаем новую
        db.execSQL(DROP_TABLE_QUERY);
        onCreate(db);
    }

    DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }*/
}
