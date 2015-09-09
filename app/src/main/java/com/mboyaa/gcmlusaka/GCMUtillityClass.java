package com.mboyaa.gcmlusaka;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Mboyaa on 09/09/2015.
 */
public class GCMUtillityClass extends ActionBarActivity {

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void iconUpdate(String name){
        if (name.equals("ZESCO")) {
            setTitle(name);
            getSupportActionBar().setIcon(R.drawable.zesco);
        } else if (name.equals("ZCAS")) {
            setTitle(name);
            getSupportActionBar().setIcon(R.drawable.zcas_logo);
        } else if (name.equals("UNZA")) {
            setTitle(name);
            getSupportActionBar().setIcon(R.drawable.unzalogo);
        } else if (name.equals("LCC")) {
            setTitle(name);
            getSupportActionBar().setIcon(R.drawable.lcc);
        }else if (name.equals("LWSC")) {
            setTitle(name);
            getSupportActionBar().setIcon(R.drawable.lwsc);
        }else if (name.equals("POLICE")) {
            setTitle(name);
            getSupportActionBar().setIcon(R.drawable.police);
        }else if (name.equals("BONGOHIVE")) {
            setTitle(name);
            getSupportActionBar().setIcon(R.drawable.hive);
        }else if (name.equals("BLOOD BANK")) {
            setTitle(name);
            getSupportActionBar().setIcon(R.drawable.znbts);
        }else if (name.equals("PARLIAMENT")) {
            setTitle(name);
            getSupportActionBar().setIcon(R.drawable.zambia);
        }else if (name.equals("FAZ")) {
            setTitle(name);
            getSupportActionBar().setIcon(R.drawable.faz);
        }else if (name.equals("CBU")) {
            setTitle(name);
            getSupportActionBar().setIcon(R.drawable.cbu);
        }else {
            getSupportActionBar().setIcon(R.drawable.no_image);
        }
    }
}
