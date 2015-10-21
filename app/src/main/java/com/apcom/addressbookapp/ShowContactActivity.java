package com.apcom.addressbookapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ShowContactActivity extends AppCompatActivity {

    private ContactsDataSource datasource;
    int id;
    TextView firstNameTextView, lastNameTextView, phoneTextView;
    String firstName, lastName, phone;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);

        intent = getIntent();
        id = intent.getIntExtra("id", 0);

        datasource = new ContactsDataSource(this);
        datasource.open();
        List<Profile> all_profiles = datasource.getAllProfiles();
        datasource.close();
        Profile profile = all_profiles.get(id);

        firstName = profile.getFirst_name();
        lastName = profile.getLast_name();
        phone = profile.getPhone();

        firstNameTextView = (TextView) findViewById(R.id.contactFirstName);
        lastNameTextView = (TextView) findViewById(R.id.contactLastName);
        phoneTextView = (TextView) findViewById(R.id.contactPhoneNumber);

        firstNameTextView.setText(firstName);
        lastNameTextView.setText(lastName);
        phoneTextView.setText(phone);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_contact, menu);
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

    public void callToUser(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        startActivity(intent);
    }

    public void sendSMS(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phone));
        startActivity(intent);
    }
}
