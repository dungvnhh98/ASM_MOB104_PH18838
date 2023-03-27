package com.example.asm_mob104_name.Mode;

import java.io.Serializable;
import java.util.List;

public class Truyen implements Serializable {
    public String idTruyen;
    public String tenTruyen;
    public String tacGia;
    public String moTa;
    public int namXB;
    public String anhBia;
    public int luotXem;
    public List<String> noiDung;
    public List<String> luotThich;

    public Truyen() {
    }

    public Truyen(String idTruyen, String tenTruyen, String tacGia, String moTa, int namXB, String anhBia, int luotXem, List<String> noiDung, List<String> luotThich) {
        this.idTruyen = idTruyen;
        this.tenTruyen = tenTruyen;
        this.tacGia = tacGia;
        this.moTa = moTa;
        this.namXB = namXB;
        this.anhBia = anhBia;
        this.luotXem = luotXem;
        this.noiDung = noiDung;
        this.luotThich = luotThich;
    }

    public String getIdTruyen() {
        return idTruyen;
    }

    public void setIdTruyen(String idTruyen) {
        this.idTruyen = idTruyen;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getNamXB() {
        return namXB;
    }

    public void setNamXB(int namXB) {
        this.namXB = namXB;
    }

    public String getAnhBia() {
        return anhBia;
    }

    public void setAnhBia(String anhBia) {
        this.anhBia = anhBia;
    }

    public int getLuotXem() {
        return luotXem;
    }

    public void setLuotXem(int luotXem) {
        this.luotXem = luotXem;
    }

    public List<String> getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(List<String> noiDung) {
        this.noiDung = noiDung;
    }

    public List<String> getLuotThich() {
        return luotThich;
    }

    public void setLuotThich(List<String> luotThich) {
        this.luotThich = luotThich;
    }
}
