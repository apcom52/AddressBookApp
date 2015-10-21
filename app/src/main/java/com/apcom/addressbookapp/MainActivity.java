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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ListView mListView;
    private ContactsDataSource dataSource;
    static final private int ADD_CONTACT = 0; //нужна для startActivityForResult

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //При запуске приложения получаем список пользователей
        mListView = (ListView) findViewById(R.id.listView);
        refreshPage((View)findViewById(R.id.RefreshPageButton));

        //Применяем слушатель событий для listView. При клике запускаем Activity, в которую передаем id элемента
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Неявный вызов Activity (через Intent Filter)
                Intent intent = new Intent("com.apcom.addressbookapp.intent.showcontactactivity");
                intent.putExtra("id", position);
                startActivity(intent);
            }
        });
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
        //Запускаем окно "Добавление контакта" - Явный вызов Activity
        Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
        startActivityForResult(intent, ADD_CONTACT);
    }

    //Функция получения списка контактов из базы и их вывод в listView
    public void refreshPage(View view) {
        dataSource = new ContactsDataSource(this);
        dataSource.open();

        List<Profile> values = dataSource.getAllProfiles();
        ArrayAdapter<Profile> adapter = new ArrayAdapter<Profile>(this, android.R.layout.simple_list_item_1, values);
        mListView.setAdapter(adapter);
    }

    //Функция, которая запускается после закрытия дочерней Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CONTACT) {
            if (resultCode == RESULT_OK) {
                refreshPage((View)findViewById(R.id.RefreshPageButton));
            }
        }
    }
}
