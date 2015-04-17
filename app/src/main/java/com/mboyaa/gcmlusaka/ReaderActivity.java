package com.mboyaa.gcmlusaka;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;


public class ReaderActivity extends ActionBarActivity {


    ListView list;
    LazyAdapter2 adapter;

    static final String KEY_ARTIST = "artist";
    static final String KEY_DURATION = "duration";
    String com_idx;
    String msg;
    String message_id;
    String description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        Intent iA = getIntent();
        com_idx = iA.getStringExtra("com_idx");
        msg = iA.getStringExtra("message");
        String msg_id = iA.getStringExtra("message_id");
        iconUpdate(com_idx);

        TextView x = (TextView)findViewById(R.id.textView);
        x.setText(msg);

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

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delete) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

            // Setting Dialog Title
            alertDialog.setTitle("Delete Message");

            // Setting Dialog Message
            alertDialog.setMessage("Do you want to delete this notification message?");

            // Setting Icon to Dialog
            alertDialog.setIcon(R.drawable.delete);

            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    Intent iA = getIntent();

                    message_id = iA.getStringExtra("message_id");
                    description = iA.getStringExtra("description");


                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                    db.deleteMessage(message_id);

                    Intent i = new Intent(getApplicationContext(),DisplayActivity.class);
                    i.putExtra("description",description);
                    i.putExtra("com_id",com_idx);
                    startActivity(i);
                    finish();
                    // User pressed YES button. Write Logic Here
                /*    Toast.makeText(getApplicationContext(), "You clicked on YES",
                            Toast.LENGTH_SHORT).show();*/
                }
            });

            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // User pressed No button. Write Logic Here
                  return;
                }
            });



    /*        // Setting Netural "Cancel" Button
            alertDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // User pressed Cancel button. Write Logic Here
                    Toast.makeText(getApplicationContext(), "You clicked on Cancel",
                            Toast.LENGTH_SHORT).show();
                }
            });*/

            // Showing Alert Message
            alertDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
