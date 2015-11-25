package com.mboyaa.gcmlusaka;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class NotificationSelector extends Activity {

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_ARTIST = "artist";
    private static final String KEY_DURATION = "duration";
    private static final String KEY_THUMB_URL = "thumb_url";
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        // DatabaseHandler db = new DatabaseHandler(getApplicationContext());



        ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

        final String[] companyDetails = this.getResources().getStringArray(R.array.companies);
        final String[] company_description = this.getResources().getStringArray(R.array.companies_desc);
        for (int i = 0; i < companyDetails.length; i++) {
            // creating new HashMap
            HashMap<String, String> map = new HashMap<String, String>();
            //Cursor cx = db.getCompUnreadMessages(companyDetails[i]);
            // adding each child node to HashMap key => value
            map.put(KEY_ID, companyDetails[i]);
            map.put(KEY_TITLE, companyDetails[i]);
            map.put(KEY_ARTIST, company_description[i]);
            //map.put(KEY_DURATION, String.valueOf(cx.getCount()));
            map.put(KEY_THUMB_URL, companyDetails[i]);

            //adding HashList to ArrayList
            songsList.add(map);
        }

        list=(ListView)findViewById(R.id.listz);

        // Getting adapter by passing xml data ArrayList
        loadNotificationComps adapter = new loadNotificationComps(this, songsList);
        list.setAdapter(adapter);

/*        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

        // Click event for single list row
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(), "Position : "+position + " ;", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), ReaderActivity.class);
                Intent iA = getIntent();
                String name = iA.getStringExtra(DatabaseHandler.KEY_COM_ID);
                i.putExtra("com_idx", name);
                TextView xc = (TextView) view.findViewById(R.id.hidden);
                i.putExtra("message", xc.getText());
                TextView nice = (TextView) view.findViewById(R.id.idx);
                i.putExtra("message_id", nice.getText());
                startActivityForResult(i, 1);
            }
        });

    }




}
