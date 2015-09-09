package com.mboyaa.gcmlusaka;

import android.content.Context;
import android.widget.ImageView;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader {

    /*    MemoryCache memoryCache=new MemoryCache();
        FileCache fileCache;*/
    private Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());
    ExecutorService executorService;

    public ImageLoader(Context context) {
//        fileCache=new FileCache(context);
        executorService = Executors.newFixedThreadPool(5);
    }

    final int zesco = R.drawable.zesco;
    final int lcc = R.drawable.lcc;
    final int lwsc = R.drawable.lwsc;
    final int police = R.drawable.police;
    final int zcas = R.drawable.zcas_logo;
    final int unza = R.drawable.unzalogo;
    final int hive = R.drawable.hive;
    final int znbts = R.drawable.znbts;
    final int zambia = R.drawable.zambia;
    final int faz = R.drawable.faz;
    final int cbu = R.drawable.cbu;
    final int def = R.drawable.no_image;

    public void DisplayImage(String url, ImageView imageView) {
        if (url.equals("ZESCO")) {
            imageView.setImageResource(zesco);
        } else if (url.equals("ZCAS")) {
            imageView.setImageResource(zcas);
        } else if (url.equals("UNZA")) {
            imageView.setImageResource(unza);
        } else if (url.equals("LCC")) {
            imageView.setImageResource(lcc);
        }else if (url.equals("LWSC")) {
            imageView.setImageResource(lwsc);
        }else if (url.equals("POLICE")) {
            imageView.setImageResource(police);
        }else if (url.equals("BONGOHIVE")) {
            imageView.setImageResource(hive);
        }else if (url.equals("BLOOD BANK")) {
            imageView.setImageResource(znbts);
        }else if (url.equals("PARLIAMENT")) {
            imageView.setImageResource(zambia);
        }else if (url.equals("FAZ")) {
            imageView.setImageResource(faz);
        }else if (url.equals("CBU")) {
            imageView.setImageResource(cbu);
        }else {
            imageView.setImageResource(def);
        }


      /* imageViews.put(imageView, url);
        Bitmap bitmap=memoryCache.get(url);
        if(bitmap!=null)
            imageView.setImageBitmap(bitmap);
        else
        {
            queuePhoto(url, imageView);
            imageView.setImageResource(stub_id);
        }*/
    }


}
