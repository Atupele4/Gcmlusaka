package com.mboyaa.gcmlusaka;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

import static com.mboyaa.gcmlusaka.CommonUtilities.SENDER_ID;
import static com.mboyaa.gcmlusaka.CommonUtilities.displayMessage;

public class GCMIntentService extends GCMBaseIntentService {

	private static final String TAG = "GCMIntentService";

    public GCMIntentService() {
        super(SENDER_ID);
    }

    /**
     * Method called on device registered
     **/
    @Override
    protected void onRegistered(Context context, String registrationId) {
        Log.i(TAG, "Device registered: regId = " + registrationId);
        displayMessage(context, "Your device registred with GCM");
        Log.d("NAME", MainActivity.name);
        ServerUtilities.register(context, MainActivity.name, MainActivity.email, registrationId);
    }



    /**
     * Method called on device un registred
     * */
    @Override
    protected void onUnregistered(Context context, String registrationId) {
        Log.i(TAG, "Device unregistered");
        displayMessage(context, getString(R.string.gcm_unregistered));
        ServerUtilities.unregister(context, registrationId);
    }

    /**
     * Method called on Receiving a new message
     * */
    @Override
    protected void onMessage(Context context, Intent intent) {
        Log.i(TAG, "Received message");
        String message = intent.getExtras().getString("message");
        String com_id = intent.getExtras().getString("com_id");
        String datesent = intent.getExtras().getString("datesent");

        if(!message.isEmpty()) {
            DatabaseHandler db = new DatabaseHandler(this);

            db.saveMessage(new Messages(com_id, message, datesent));

            displayMessage(context, message);
            // notifies user
            generateNotification(context, message, com_id);
        }
    }

    /**
     * Method called on receiving a deleted message
     * */
    @Override
    protected void onDeletedMessages(Context context, int total) {
        Log.i(TAG, "Received deleted messages notification");
        String message = getString(R.string.gcm_deleted, total);
        displayMessage(context, message);
        // notifies user
        String messagex = message.substring(25);
        generateNotification(context, message,"");
    }

    /**
     * Method called on Error
     * */
    @Override
    public void onError(Context context, String errorId) {
        Log.i(TAG, "Received error: " + errorId);
        displayMessage(context, getString(R.string.gcm_error, errorId));
    }

    @Override
    protected boolean onRecoverableError(Context context, String errorId) {
        // log message
        Log.i(TAG, "Received recoverable error: " + errorId);
        displayMessage(context, getString(R.string.gcm_recoverable_error,
                errorId));
        return super.onRecoverableError(context, errorId);
    }

    /**
     * Issues a notification to inform the user that server has sent a message.
     */
    public static void generateNotification(Context context, String message, String com_id) {
        int icon = setNotificationIcon(com_id);
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, message, when);
        
        String title = context.getString(R.string.app_name);
        
        Intent notificationIntent = new Intent(context, DisplayActivity.class);
        notificationIntent.putExtra("com_id", com_id);
        // set intent so it does not start a new activity
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                //.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent intent =
                PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setLatestEventInfo(context, title, message, intent);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        
        // Play default notification sound
        notification.defaults |= Notification.DEFAULT_SOUND;
        
        //notification.sound = Uri.parse("android.resource://" + context.getPackageName() + "your_sound_file_name.mp3");
        
        // Vibrate if vibrate is enabled
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notificationManager.notify(0, notification);
    }

    private static int setNotificationIcon(String com_id){

        if (com_id.equals("ZESCO")) {
            return R.drawable.zesco;
        } else if (com_id.equals("ZCAS")) {
            return R.drawable.zcas_logo;
        } else if (com_id.equals("UNZA")) {
            return R.drawable.unzalogo;
        } else if (com_id.equals("LCC")) {
            return R.drawable.lcc;
        }else if (com_id.equals("LWSC")) {
            return R.drawable.lwsc;
        }else if (com_id.equals("POLICE")) {
            return R.drawable.police;
        }else if (com_id.equals("BONGOHIVE")) {
            return R.drawable.hive;
        }else if (com_id.equals("BLOOD BANK")) {
            return R.drawable.znbts;
        }else if (com_id.equals("PARLIAMENT")) {
            return R.drawable.zambia;
        }else if (com_id.equals("FAZ")) {
            return R.drawable.faz;
        }else if (com_id.equals("CBU")) {

            return R.drawable.cbu;
        }else {
            return R.drawable.pin;
        }
    }

}
