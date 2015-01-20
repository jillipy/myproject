package com.example.jillipi.bythebolt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.jillipi.bythebolt.data.Fabric;
import com.example.jillipi.bythebolt.data.FabricsDataManagment;


public class DetailsFabric extends Activity {
    private static final String TAG = "DetailsFabric";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_fabric);

        //getting intent and fabric ID
        Intent intent = getIntent();
        long fabricId = intent.getLongExtra(MainActivity.CLICKED_FABRIC_ID,-1);

        if (fabricId == -1){
            Log.d(TAG, "issue occured with getting fabric ID" + fabricId);
        }

        //Setting up database helper and retreaving fabric details based on ID.
        Fabric fabric = null;
        FabricsDataManagment dbHelper = new FabricsDataManagment(this);
        try{
            dbHelper.open();
            fabric = dbHelper.getFabricById(fabricId);
            dbHelper.close();
        }
        catch (Exception e) {
            Log.d(TAG,"Something went wrong with getting fabric based on id. exception: " + e);
        }

        TextView textView = new TextView(this);
        textView.setText("Fabric ID: " + fabricId + " Fabric name: " + fabric.getName());

        setContentView(textView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details_fabric, menu);
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
}
