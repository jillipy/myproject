package com.example.jillipi.bythebolt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jillipi.bythebolt.data.Fabric;
import com.example.jillipi.bythebolt.data.FabricsDataManagment;
import com.example.jillipi.bythebolt.support.FabricsListAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * This activity displays the list view of all fabrics.
 * It should be the hierarchical parent of all other pages.
 * Default view sorted by Date
 */

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    public static final String CLICKED_FABRIC_ID = "com.example.jillipi.bythebolt.CLICKED_FABRIC_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting up database helper and getting list of fabrics
        FabricsDataManagment dbHelper = new FabricsDataManagment(this);
        //cleaning out database to start with
        try {
            dbHelper.open();
            dbHelper.deleteData();
            dbHelper.close();
        }
        catch (Exception e){
            Log.d(TAG, "error occured when trying to delete data from table. Exception: "+e);
        }
        //adding test data to db
        addTestData();

        //retreaving test data from database
        List<Fabric> fabricList = null;
        try {
            dbHelper.open();
            fabricList = dbHelper.getAllFabrics();
            dbHelper.close();
        }
        catch (Exception e){
            Log.d(TAG, "error occured when trying to get a full list of all fabrics");
        }



        //setting up a list view
        final ListView fabricsListView = (ListView) findViewById(R.id.list_view);
        fabricsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fabric fabric = (Fabric)fabricsListView.getItemAtPosition(position);
                displayFabricDetails(fabric);

            }
        });
        FabricsListAdapter fabricsListAdapter = new FabricsListAdapter(this,R.layout.fabric_list_entry_item);
        fabricsListView.setAdapter(fabricsListAdapter);

        //adding items to the list view
        for (Fabric f : fabricList){
            fabricsListAdapter.add(f);
        }

    }

    private void displayFabricDetails(Fabric fabric){
        Intent intent = new Intent(this, DetailsFabric.class);
        intent.putExtra(CLICKED_FABRIC_ID,fabric.getId());
        startActivity(intent);
    }

    private ArrayList<Fabric> addTestData(){

        ArrayList<Fabric> fabricsList = new ArrayList<Fabric>();

        Fabric fabric = new Fabric();
        fabric.setDate("test1Date");
        fabric.setName("test1Name");
        fabric.setType("test1Type");
        fabric.setYardage("test1Yards");
        fabricsList.add(fabric);

        Fabric fabric1 = new Fabric();
        fabric1.setDate("test2Date");
        fabric1.setName("test2Name");
        fabric1.setType("test2Type");
        fabric1.setYardage("test2Yards");
        fabricsList.add(fabric1);

        Fabric fabric2 = new Fabric();
        fabric2.setDate("test3Date");
        fabric2.setName("test3Name");
        fabric2.setType("test3Type");
        fabric2.setYardage("test3Yards");
        fabricsList.add(fabric2);

        FabricsDataManagment dbHelper = new FabricsDataManagment(getApplicationContext());
        for (Fabric f : fabricsList) {
            try {
                Log.d(TAG, "adding new fabric object to db");
                dbHelper.open();
                dbHelper.insertFabric(f);
                dbHelper.close();
            } catch (Exception e) {
                Log.d(TAG, "ran into exception: " + e.toString());
            }
        }

        return fabricsList;

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
}
