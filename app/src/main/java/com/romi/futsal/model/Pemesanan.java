package com.romi.futsal.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;



@IgnoreExtraProperties
public class Pemesanan implements Serializable {

    private String nama,nohp,lapangan,tanggal,mulai,selesai,tim,key;

    public Pemesanan() {
    }

    public Pemesanan(String nama, String nohp, String lapangan, String tanggal, String mulai, String selesai, String tim) {
        this.nama = nama;
        this.nohp = nohp;
        this.lapangan = lapangan;
        this.tanggal = tanggal;
        this.mulai = mulai;
        this.selesai = selesai;
        this.tim = tim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getLapangan() {
        return lapangan;
    }

    public void setLapangan(String lapangan) {
        this.lapangan = lapangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getMulai() {
        return mulai;
    }

    public void setMulai(String mulai) {
        this.mulai = mulai;
    }

    public String getSelesai() {
        return selesai;
    }

    public void setSelesai(String selesai) {
        this.selesai = selesai;
    }

    public String getTim() {
        return tim;
    }

    public void setTim(String tim) {
        this.tim = tim;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return " "+nama+"\n" +
                " "+nohp+"\n" +
                " "+lapangan+"\n" +
                " "+tanggal+"\n" +
                " "+mulai+"\n" +
                " "+selesai+"\n" +
                " "+tim;
    }
}
