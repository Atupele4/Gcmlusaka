package com.mboyaa.gcmlusaka;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        Intent iA = getIntent();
        com_idx = iA.getStringExtra("com_idx");
        msg = iA.getStringExtra("message");
        String msg_id = iA.getStringExtra("message_id");
        GCMUtillityClass iconChanger = new GCMUtillityClass();
        iconChanger.iconUpdate(com_idx);

        TextView x = (TextView)findViewById(R.id.textView);
        x.setText(msg);

        //updateMessageViewed
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        db.updateMessageViewed(msg_id);
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

                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                    db.deleteMessage(message_id);

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
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
