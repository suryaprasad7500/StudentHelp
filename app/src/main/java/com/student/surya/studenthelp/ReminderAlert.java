package com.student.surya.studenthelp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

/**
 * Created by Surya on 11/18/2015.
 */
public class ReminderAlert extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        createNotification(context, "Reminder!", "Class in 15 mins!");
    }

    public void createNotification(Context context, String msg, String msgText){
        PendingIntent notificationIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notif = new NotificationCompat.Builder(context);
        notif.setSmallIcon(R.mipmap.ic_launcher);
        notif.setContentTitle(msg);
        notif.setContentText(msgText);

        notif.setContentIntent(notificationIntent);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(4646, notif.build());
    }
}
