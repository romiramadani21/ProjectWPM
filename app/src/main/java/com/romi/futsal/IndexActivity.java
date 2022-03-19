package com.romi.futsal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;


public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }

    //function untuk Cardview pada pilihan dashboard
    public void peraturan(View view) {
        Intent intent= new Intent(this, PeraturanActivity.class);
        startActivity(intent);
    }
    public void tips(View view) {
        Intent intent= new Intent(this, TipsActivity.class);
        startActivity(intent);
    }
    public void fasilitas(View view) {
        Intent intent= new Intent(this, FasilitasActivity.class);
        startActivity(intent);
    }

    public void pemesanan(View view) {
        Intent intent= new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    public void lokasi(View view) {
        Intent intent= new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void pengingat(View view) {
        Intent intent= new Intent(this, PengingatActivity.class);
        startActivity(intent);
    }

    //Function untuk Membaca Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu,menu);
        return true;
    }

    //Function untuk Option menu yang dipilih
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.pemesanan){
            startActivity(new Intent(this,PemesananActivity.class));
        }else if(item.getItemId()==R.id.fasilitas){
            startActivity(new Intent(this,FasilitasActivity.class));
        }else if(item.getItemId()==R.id.lokasi){
            startActivity(new Intent(this,MapsActivity.class));
        }else if(item.getItemId()==R.id.tips){
            startActivity(new Intent(this,TipsActivity.class));
        }else if(item.getItemId()==R.id.peraturan){
            startActivity(new Intent(this,PeraturanActivity.class));
        }else if(item.getItemId()==R.id.pengingat){
            startActivity(new Intent(this,PengingatActivity.class));
        }else if(item.getItemId()==R.id.keluar){
            startActivity(new Intent(this,LoginActivity.class));
        }
        return true;
    }

}
