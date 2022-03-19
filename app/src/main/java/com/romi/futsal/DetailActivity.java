package com.romi.futsal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.romi.futsal.model.Pemesanan;

public class DetailActivity extends AppCompatActivity {

    private Button btSubmit;
    private EditText etNama,etNohp,etLapangan,etTanggal,etMulai,etSelesai,etTim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);


        // inisialisasi fields EditText dan Button
        etNama = (EditText) findViewById(R.id.et_nama);
        etNohp = (EditText) findViewById(R.id.et_nohp);
        etLapangan = (EditText) findViewById(R.id.et_lapangan);
        etTanggal = (EditText) findViewById(R.id.et_tanggal);
        etMulai = (EditText) findViewById(R.id.et_mulai);
        etSelesai = (EditText) findViewById(R.id.et_selesai);
        etTim = (EditText) findViewById(R.id.et_tim);
        btSubmit = (Button) findViewById(R.id.bt_submit);

        etNama.setEnabled(false);
        etNohp.setEnabled(false);
        etLapangan.setEnabled(false);
        etTanggal.setEnabled(false);
        etMulai.setEnabled(false);
        etSelesai.setEnabled(false);
        etTim.setEnabled(false);
        btSubmit.setVisibility(View.GONE);

        Pemesanan barang = (Pemesanan) getIntent().getSerializableExtra("data");
        if(barang!=null){
            etNama.setText(barang.getNama());
            etNohp.setText(barang.getNohp());
            etLapangan.setText(barang.getLapangan());
            etTanggal.setText(barang.getTanggal());
            etMulai.setText(barang.getMulai());
            etSelesai.setText(barang.getSelesai());
            etTim.setText(barang.getTim());
        }
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, DetailActivity.class);
    }

}
