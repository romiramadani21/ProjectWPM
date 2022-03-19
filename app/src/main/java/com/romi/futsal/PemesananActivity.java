package com.romi.futsal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.romi.futsal.model.Pemesanan;

public class PemesananActivity extends AppCompatActivity {

    // variable yang merefers ke Firebase Realtime Database
    private DatabaseReference database;

    // variable fields EditText dan Button
    private Button btSubmit;
    private EditText etNama,etNohp,etLapangan,etTanggal,etMulai,etSelesai,etTim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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

        // mengambil referensi ke Firebase Database
        database = FirebaseDatabase.getInstance().getReference();

        final Pemesanan pesan = (Pemesanan) getIntent().getSerializableExtra("data");

        if (pesan != null) {
            etNama.setText(pesan.getNama());
            etNohp.setText(pesan.getNohp());
            etLapangan.setText(pesan.getLapangan());
            etTanggal.setText(pesan.getTanggal());
            etMulai.setText(pesan.getMulai());
            etSelesai.setText(pesan.getSelesai());
            etTim.setText(pesan.getTim());
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pesan.setNama(etNama.getText().toString());
                    pesan.setNohp(etNohp.getText().toString());
                    pesan.setLapangan(etLapangan.getText().toString());
                    pesan.setTanggal(etTanggal.getText().toString());
                    pesan.setMulai(etMulai.getText().toString());
                    pesan.setSelesai(etSelesai.getText().toString());
                    pesan.setTim(etTim.getText().toString());

                    updatePesan(pesan);
                }
            });
        } else {
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!isEmpty(etNama.getText().toString()) && !isEmpty(etNohp.getText().toString()) && !isEmpty(etLapangan.getText().toString()) && !isEmpty(etTanggal.getText().toString()) && !isEmpty(etMulai.getText().toString()) && !isEmpty(etSelesai.getText().toString()) && !isEmpty(etTim.getText().toString()))
                        submitPesan(new Pemesanan(etNama.getText().toString(), etNohp.getText().toString(), etLapangan.getText().toString(), etTanggal.getText().toString(), etMulai.getText().toString(), etSelesai.getText().toString(), etTim.getText().toString()));
                    else
                        Snackbar.make(findViewById(R.id.bt_submit), "Data Pemesanan tidak boleh kosong", Snackbar.LENGTH_LONG).show();

                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(
                            etNama.getWindowToken(), 0);

                }
            });
        }
    }

    private boolean isEmpty(String s){
        // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }

    private void updatePesan(Pemesanan pesan) {
        /**
         * Baris kode yang digunakan untuk mengupdate data Pemesanan
         * yang sudah dimasukkan di Firebase Realtime Database
         */
        database.child("Pemesanan") //akses parent index, ibaratnya seperti nama tabel
                .child(pesan.getKey()) //select pemesanan berdasarkan key
                .setValue(pesan) //set value pemesanan yang baru
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        /**
                         * Baris kode yang akan dipanggil apabila proses update pemesanan sukses
                         */
                        Snackbar.make(findViewById(R.id.bt_submit), "Data berhasil diupdate", Snackbar.LENGTH_LONG).setAction("Oke", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        }).show();
                    }
                });
    }

    private void submitPesan(Pemesanan pesan) {
        /**
         * Ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime Database
         * dan juga kita set onSuccessListener yang berisi kode yang akan dijalankan
         * ketika data berhasil ditambahkan
         */
        database.child("Pemesanan").push().setValue(pesan).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                etNama.setText("");
                etNohp.setText("");
                etLapangan.setText("");
                etTanggal.setText("");
                etMulai.setText("");
                etSelesai.setText("");
                etTim.setText("");
                Snackbar.make(findViewById(R.id.bt_submit), "Pemesanan Berhasil", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, PemesananActivity.class);
    }
}
