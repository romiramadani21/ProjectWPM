package com.romi.futsal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

public class MyAlarm extends BroadcastReceiver {

    //method yang dipanggil ketika alarm dipicu

    @Override
    public void onReceive(Context context, Intent intent){

        //Memberikan sound ketika alarm menyala
        MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_ALARM_ALERT_URI);
        mediaPlayer.start();

        //menampilkan output dengan toast
        Toast.makeText(context,"Saat ini Ada Jadwal Bermain Futsal!",Toast.LENGTH_SHORT).show();

        //menampilkan output dilog
        Log.d("Alarm","Alarm baru menyala");
    }
}
