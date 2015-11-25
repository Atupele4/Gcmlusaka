package com.mboyaa.gcmlusaka;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class LazyAdapter2 extends BaseAdapter {

    private final Activity activity;
    private final ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    private ImageLoader imageLoader;

    public LazyAdapter2(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {

        HashMap<String, String> song;
        song = data.get(position);


        View vi;
        if(song.get("msg_status").equals("2"))
        {
            vi = inflater.inflate(R.layout.list_msg_not_read, null);
        }else {
            vi = inflater.inflate(R.layout.list_row2, null);
        }

        TextView artist = (TextView)vi.findViewById(R.id.company_description); // artist name
        TextView msg = (TextView)vi.findViewById(R.id.hidden); // hidden message
        TextView idx = (TextView)vi.findViewById(R.id.idx); // get hidden message id
        TextView duration = (TextView)vi.findViewById(R.id.duration); // duration

        

        // Setting all values in list view
        //msg_status
        duration.setText(song.get(CustomizedListView.KEY_DURATION));
        String xc = song.get(CustomizedListView.KEY_ARTIST);
        artist.setText(xc);
        msg.setText(song.get(CustomizedListView.KEY_MSG));
        idx.setText(song.get(CustomizedListView.KEY_ID));
        return vi;
    }
}