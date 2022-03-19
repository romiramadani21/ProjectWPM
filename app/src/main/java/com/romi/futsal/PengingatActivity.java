package com.romi.futsal;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;

public class PengingatActivity extends AppCompatActivity {
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengingat);

        //mendapatkan objectb timePacker
        timePicker =(TimePicker) findViewById(R.id.timePicker);

        //membuat aksi ketika button ditekan
        findViewById(R.id.buttonAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //object kalender yang digunakan untuk mendapatkan waktu yang spesifik dalam milis
                //method alaram manager mengambil waktu dari milis untuk mengatur alarm
                Calendar calendar = Calendar.getInstance();
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getCurrentHour(),timePicker.getMinute(),0);
                }else{
                    calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getCurrentHour(),timePicker.getCurrentMinute(),0);
                }
                setAlarm(calendar.getTimeInMillis());
            }
        });
    }

    private void setAlarm(long time){
        //mengambil alarmManager
        AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        //membuat sebuah intent baru untuk spesifikasi penerima broadcast
        Intent i = new Intent(this,MyAlarm.class);

        //membuat pendingintent menggunakan intent
        PendingIntent pi = PendingIntent.getBroadcast(this,0,i,0);

        //mengatur alarm secara berulang setia harinya
        am.setRepeating(AlarmManager.RTC,time,AlarmManager.INTERVAL_DAY,pi);
        Toast.makeText(this,"Jadwal telah diatur",Toast.LENGTH_SHORT).show();

    }
}