package com.hfad.uts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Pembelian implements Serializable {

    private Date tanggalPembelian;
    private String alamat;
    private int total;
    private ArrayList<Order> al = new ArrayList<Order>();

    public Pembelian(Date tanggalPembelian, String alamat, int total, ArrayList<Order> al) {
        this.tanggalPembelian = tanggalPembelian;
        this.alamat = alamat;
        this.total = total;
        this.al = al;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTanggalPembelian(Date tanggalPembelian) {
        this.tanggalPembelian = tanggalPembelian;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setAl(ArrayList<Order> al) {
        this.al = al;
    }

    public Date getTanggalPembelian() {
        return tanggalPembelian;
    }

    public String getAlamat() {
        return alamat;
    }

    public ArrayList<Order> getAl() {
        return al;
    }
}
