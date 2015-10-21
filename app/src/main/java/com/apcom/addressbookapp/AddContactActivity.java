package com.apcom.addressbookapp;

import android.content.ContentValues;
import android.content.Intent;
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

        datasource = new ContactsDataSource(this);
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

    //Добавление контакта в базу данных
    public void clickAddContactToDBBtn(View view) {
        String firstName = ((EditText)findViewById(R.id.textEditFirstName)).getText().toString();
        String lastName = ((EditText)findViewById(R.id.textEditLastName)).getText().toString();
        String phone = ((EditText)findViewById(R.id.textEditPhone)).getText().toString();

        datasource.open();
        datasource.createProfile(firstName, lastName, phone);
        datasource.close();

        //Возвращаем в родительскую Activity положительный ответ
        setResult(RESULT_OK, new Intent());
        this.finish();
    }
}
