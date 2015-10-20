package com.apcom.addressbookapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class AddContactActivity extends AppCompatActivity {

    private ContactsDataSource datasource;

    EditText first_name_edit, last_name_edit, phone_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        first_name_edit = (EditText) findViewById(R.id.textEditFirstName);
        last_name_edit = (EditText) findViewById(R.id.textEditLastName);
        phone_edit = (EditText) findViewById(R.id.textEditPhone);

        /*mDatabase = new DBHelper(this, "contactsapp.db", null, 1);
        mSqliteDatabase = mDatabase.getWritableDatabase();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clickAddContactToDBBtn(View view) {
        /*String firstName = first_name_edit.getText().toString();
        String lastName = last_name_edit.getText().toString();
        String phone = phone_edit.getText().toString();*/
        /*Profile profile = null;1
        profile = */
        /*Log.w(AddContactActivity.class.getName(), firstName);
        Log.w(AddContactActivity.class.getName(), lastName);
        Log.w(AddContactActivity.class.getName(), phone);*/
        datasource = new ContactsDataSource(this);
        datasource.createProfile("asd", "qwe", "123");
        //this.finish();
    }
}
