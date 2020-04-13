/*
 * Copyright (c) 2020.
 * Name: Emmanuel Sackey
 * Matric: S1719015
 * Programme: Bsc(Hons) Computing
 *
 */

package com.alueducation.quakepal.helper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.alueducation.quakepal.R;
import com.alueducation.quakepal.view.AboutActivity;

import java.net.MalformedURLException;
import java.net.URL;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.menuAbout:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;

            case R.id.menuRefresh:
                String url = "https://quakes.bgs.ac.uk/feeds/WorldSeismology.xml";
                final String TAG = "Refresh Menu";
                try {
                    System.out.println("Refreshing data");
                    new FeedParser().execute(new URL( url));
                    Toast toast = Toast.makeText(getApplicationContext(), "Data has been refreshed successfully", Toast.LENGTH_LONG);
                    toast.show();
                    System.out.println("Data refreshed");
                } catch (MalformedURLException ex){
                    Log.e(TAG, ex.toString());
                    Toast toast = Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT);
                    toast.show();
                }

                break;
        }
        return true;
    }
}
