package com.apcom.addressbookapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ListView mListView;
    private ContactsDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);
        refreshPage((View)findViewById(R.id.RefreshPageButton));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void clickAddContactBtn(View view) {
        //Запускаем окно "Добавление контакта"
        Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
        startActivity(intent);
    }

    public void refreshPage(View view) {
        dataSource = new ContactsDataSource(this);
        dataSource.open();

        List<Profile> values = dataSource.getAllProfiles();
        ArrayAdapter<Profile> adapter = new ArrayAdapter<Profile>(this, android.R.layout.simple_list_item_1, values);
        mListView.setAdapter(adapter);

        Log.w(MainActivity.class.getName(), "refresh");
    }
}
