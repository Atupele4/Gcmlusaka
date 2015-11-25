package com.mboyaa.gcmlusaka;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.members.systems.CompanyDetails;

import java.util.ArrayList;

public class ListCompanyAdapter extends BaseAdapter {

    private ArrayList<CompanyDetails> companyDetails_;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;

    public ListCompanyAdapter(Activity a, ArrayList<CompanyDetails> companyDetails) {
        Activity activity = a;
        companyDetails_= companyDetails;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return companyDetails_.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.company_list_row, null);

        TextView companyName = (TextView)vi.findViewById(R.id.company_name); // title
        TextView companyDescription = (TextView)vi.findViewById(R.id.company_description); // artist name
        //TextView duration = (TextView)vi.findViewById(R.id.duration); // duration
        ImageView companyImage = (ImageView)vi.findViewById(R.id.company_image); // thumb image
        
        CompanyDetails companyDetails = new CompanyDetails();
        companyDetails = companyDetails_.get(position);
        
        // Setting all values in listview
        companyName.setText(companyDetails.GetCompanyName());
        companyDescription.setText(companyDetails.GetCompanyDescription());
        imageLoader.DisplayImage(companyDetails.GetCompanyImageID(), companyImage);
        return vi;
    }
}