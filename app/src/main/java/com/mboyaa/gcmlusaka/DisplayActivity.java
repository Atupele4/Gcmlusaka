package com.mboyaa.gcmlusaka;

import android.annotation.TargetApi;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class DisplayActivity extends ActionBarActivity {

    static final String KEY_SONG = "song"; // parent node
    static final String KEY_ID = "id";

    static final String KEY_ARTIST = "artist";
    static final String KEY_DURATION = "duration";
   // static final String KEY_ID = "COM_ID";
   String description;
   String com_id;
    DatabaseHandler db;
    Cursor cx;
    ListView list;
    LazyAdapter2 adapter;

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        // Getting name, email from intent
        Intent iA = getIntent();

        com_id = iA.getStringExtra("com_id");
        description = iA.getStringExtra("description");
        iconUpdate(com_id);

        populateMessages(com_id);

        // Click event for single list row
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //Toast.makeText(getApplicationContext(),tempArray[position],Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),ReaderActivity.class);
                Intent iA = getIntent();

                String name = iA.getStringExtra("com_id");
                i.putExtra("com_idx",name);
                i.putExtra("description",description);

                TextView xc = (TextView)view.findViewById(R.id.hidden);
                i.putExtra("message",xc.getText());

                TextView nice = (TextView)view.findViewById(R.id.idx);
                i.putExtra("message_id",nice.getText());

                startActivity(i);


            }
        });

    }


    public void populateMessages(String com_id){
        db = new DatabaseHandler(getApplicationContext());

        ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

        cx = db.getCompCount(com_id);

        // final String[] tempArray = this.getResources().getStringArray(R.array.companies);
        if (cx.moveToFirst()){
            do{
                HashMap<String, String> map = new HashMap<String, String>();

                map.put(KEY_DURATION, cx.getString((cx.getColumnIndex("datetimesent"))));
                String zx = cx.getString((cx.getColumnIndex("message")));
                String id = cx.getString((cx.getColumnIndex("_id")));
                map.put("id",id);
                map.put("msg",zx);
                if(zx.length() > 45) {
                    map.put(KEY_ARTIST, zx.substring(0, 45) + "...");
                }else {
                    map.put(KEY_ARTIST, zx);
                }

                songsList.add(map);
                // do what ever you want here
            }while(cx.moveToNext());
        }
        //cx.close();
        list=(ListView)findViewById(R.id.listz);
        // Getting adapter by passing xml data ArrayList
        adapter=new LazyAdapter2(this, songsList);
        list.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Getting name, email from intent
        Intent iA = getIntent();

        com_id = iA.getStringExtra("com_id");
        description = iA.getStringExtra("description");

        populateMessages(com_id);
        //finish();
        //Toast.makeText(getApplicationContext(), com_id + " - " + description, Toast.LENGTH_LONG).show();
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    protected void iconUpdate(String name){
        if (name.equals("ZESCO")) {
            setTitle(name);
            getActionBar().setIcon(R.drawable.zesco);
        } else if (name.equals("ZCAS")) {
            setTitle(name);
            getActionBar().setIcon(R.drawable.zcas_logo);
        } else if (name.equals("UNZA")) {
            setTitle(name);
            getActionBar().setIcon(R.drawable.unzalogo);
        } else if (name.equals("LCC")) {
            setTitle(name);
            getActionBar().setIcon(R.drawable.lcc);
        }else if (name.equals("LWSC")) {
            setTitle(name);
            getActionBar().setIcon(R.drawable.lwsc);
        }else if (name.equals("POLICE")) {
            setTitle(name);
            getActionBar().setIcon(R.drawable.police);
        }else if (name.equals("BONGOHIVE")) {
            setTitle(name);
            getActionBar().setIcon(R.drawable.hive);
        }else if (name.equals("BLOOD BANK")) {
            setTitle(name);
            getActionBar().setIcon(R.drawable.znbts);
        }else if (name.equals("PARLIAMENT")) {
            setTitle(name);
            getActionBar().setIcon(R.drawable.zambia);
        }else if (name.equals("FAZ")) {
            setTitle(name);
            getActionBar().setIcon(R.drawable.faz);
        }else if (name.equals("CBU")) {
            setTitle(name);
            getActionBar().setIcon(R.drawable.cbu);
        }else {
            getActionBar().setIcon(R.drawable.no_image);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_searchx:
                //Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),Company_Details_Activity.class);
                Intent iA = getIntent();
                String name = iA.getStringExtra("com_id");
                i.putExtra("com_idx",name);
                i.putExtra("description",description);
                startActivity(i);
                return true;
            case R.id.action_settings:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
