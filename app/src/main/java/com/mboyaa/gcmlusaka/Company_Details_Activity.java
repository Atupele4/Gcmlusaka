package com.mboyaa.gcmlusaka;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class Company_Details_Activity extends ActionBarActivity {

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company);

        Intent iA = getIntent();
        String Company_ID = iA.getStringExtra("com_idx");
        String description = iA.getStringExtra("description");
        TextView tv =(TextView)findViewById(R.id.com_description);

        GCMUtillityClass x = new GCMUtillityClass();
        x.iconUpdate(Company_ID);
        tv.setText(description);

       // Toast.makeText(getApplicationContext(), com_desc[0], Toast.LENGTH_LONG).show();
     /*   // Getting name, email from intent
        Intent iA = getIntent();

        String name = iA.getStringExtra("com_id");
        iconUpdate(name);

        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

        Cursor cx = db.getCompCount(name);


        if (cx.moveToFirst()){
            do{
                HashMap<String, String> map = new HashMap<String, String>();

                map.put(KEY_DURATION, cx.getString((cx.getColumnIndex("datetimesent"))));
                map.put(KEY_ARTIST, cx.getString((cx.getColumnIndex("message"))));

                songsList.add(map);
                // do what ever you want here
            }while(cx.moveToNext());
        }
        //cx.close();
        list=(ListView)findViewById(R.id.listz);

        // Getting adapter by passing xml data ArrayList
        adapter=new LazyAdapter2(this, songsList);
        list.setAdapter(adapter);*/
    }


/*
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
                Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(),"2",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
