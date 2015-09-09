package com.mboyaa.gcmlusaka;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MyList extends ActionBarActivity implements ActionBar.OnNavigationListener {

    static final String KEY_SONG = "song"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_TITLE = "title";
    static final String KEY_ARTIST = "artist";
    static final String KEY_DURATION = "duration";
    static final String KEY_THUMB_URL = "thumb_url";

    ListView list;
    LazyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

        final String[] tempArray = this.getResources().getStringArray(R.array.companies);
        final String[] com_desc = this.getResources().getStringArray(R.array.companies_desc);

        //update listview UI;
        UpdateListView();

        // Click event for single list row
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(getApplicationContext(),tempArray[position],Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), DisplayActivity.class);
                i.putExtra("com_id", tempArray[position]);
                i.putExtra("description", com_desc[position]);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //update listview UI
        UpdateListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mylist_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.clearAllNotifications:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

                // Setting Dialog Title
                alertDialog.setTitle("Clear All stored Notifications");
                // Setting Dialog Message
                alertDialog.setMessage("Are you sure you want to Clear all stores Notifications");
                // Setting Icon to Dialog
                alertDialog.setIcon(R.drawable.delete);
                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                       db.ClearNotificationsMessage("ClearAll");
                        Intent i = new Intent(getApplicationContext(),MyList.class);
                        startActivity(i);
                        finish();
                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed No button. Write Logic Here
                    }
                });

                // Showing Alert Message
                alertDialog.show();
                return true;

            case R.id.action_settings:
                Toast.makeText(getApplicationContext(),"2",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        return false;
    }

    private void UpdateListView()
    {
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

        final String[] companyDetails = this.getResources().getStringArray(R.array.companies);
        final String[] company_discription = this.getResources().getStringArray(R.array.companies_desc);
        Cursor c = db.getAllContacts();
        for (int i = 0; i < companyDetails.length; i++) {
            // creating new HashMap
            HashMap<String, String> map = new HashMap<String, String>();
            Cursor cx = db.getCompCount(companyDetails[i]);
            String num = String.valueOf(c.getCount());
            // adding each child node to HashMap key => value
            map.put(KEY_ID, companyDetails[i]);
            map.put(KEY_TITLE, companyDetails[i]);
            map.put(KEY_ARTIST, company_discription[i]);
            map.put(KEY_DURATION, String.valueOf(cx.getCount()));
            map.put(KEY_THUMB_URL, companyDetails[i]);

            //adding HashList to ArrayList
            songsList.add(map);
        }

        list=(ListView)findViewById(R.id.list);

        // Getting adapter by passing xml data ArrayList
        adapter=new LazyAdapter(this, songsList);
        list.setAdapter(adapter);
    }
}