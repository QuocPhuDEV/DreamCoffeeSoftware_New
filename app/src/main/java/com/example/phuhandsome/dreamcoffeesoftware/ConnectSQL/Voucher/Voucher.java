package com.example.phuhandsome.dreamcoffeesoftware.ConnectSQL.Voucher;

public class Voucher {
    private int ID_Voucher;
    private String voucher;
    private int numberPercent;

    public Voucher(int ID_Voucher, String voucher, int numberPercent) {
        this.ID_Voucher = ID_Voucher;
        this.voucher = voucher;
        this.numberPercent = numberPercent;
    }

    public void setID_Voucher(int ID_Voucher) {
        this.ID_Voucher = ID_Voucher;
    }

    public int getID_Voucher() {
        return ID_Voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setNumberPercent(int numberPercent) {
        this.numberPercent = numberPercent;
    }

    public int getNumberPercent() {
        return numberPercent;
    }
}
