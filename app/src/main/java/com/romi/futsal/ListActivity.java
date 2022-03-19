package com.romi.futsal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.romi.futsal.adapter.AdapterRecyclerView;
import com.romi.futsal.model.Pemesanan;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements AdapterRecyclerView.FirebaseDataListener {
    /**
     * Mendefinisikan variable yang akan dipakai
     */
    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Pemesanan> daftar;

    private FloatingActionButton fab_add;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Mengeset layout
         */
        setContentView(R.layout.activity_list);
        fab_add = findViewById(R.id.fab_add);

        /**
         * Inisialisasi RecyclerView & komponennya
         */
        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        /**
         * Inisialisasi dan mengambil Firebase Database Reference
         */
        database = FirebaseDatabase.getInstance().getReference();

        /**
         * Mengambil data Pemesanan dari Firebase Realtime DB
         */
        database.child("Pemesanan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                /**
                 * Saat ada data baru, masukkan datanya ke ArrayList
                 */
                daftar = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    /**
                     * Mapping data pada DataSnapshot ke dalam object Pemesanan
                     * Dan juga menyimpan primary key pada object Pemesanan
                     * untuk keperluan Edit dan Delete data
                     */
                    Pemesanan pesan = noteDataSnapshot.getValue(Pemesanan.class);
                    pesan.setKey(noteDataSnapshot.getKey());

                    /**
                     * Menambahkan object Pemesanan yang sudah dimapping
                     * ke dalam ArrayList
                     */
                    daftar.add(pesan);
                }

                /**
                 * Inisialisasi adapter dan data Pemesanan dalam bentuk ArrayList
                 * dan mengeset Adapter ke dalam RecyclerView
                 */
                adapter = new AdapterRecyclerView(daftar, ListActivity.this);
                rvView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                /**
                 * Kode ini akan dipanggil ketika ada error dan
                 * pengambilan data gagal dan memprint error nya
                 * ke LogCat
                 */
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListActivity.this, PemesananActivity.class));
            }
        });
    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, ListActivity.class);
    }

    @Override
    public void onDeleteData(Pemesanan pesan, final int position) {
        /**
         * Kode ini akan dipanggil ketika method onDeleteData
         * dipanggil dari adapter lewat interface.
         * Yang kemudian akan mendelete data di Firebase Realtime DB
         * berdasarkan key Pemesanan.
         * Jika sukses akan memunculkan SnackBar
         */
        if(database!=null){
            database.child("Pemesanan").child(pesan.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(ListActivity.this,"Pemesanan dihapus", Toast.LENGTH_LONG).show();
                }
            });

        }
    }
}
