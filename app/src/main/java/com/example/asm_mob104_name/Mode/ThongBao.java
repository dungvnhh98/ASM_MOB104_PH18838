package com.example.asm_mob104_name.Mode;

import java.io.Serializable;
import java.util.Date;

public class ThongBao implements Serializable {
    String id;
    String username;
    String idcomic;
    Boolean check;
    Date thoigian;
    String noidung;

    public ThongBao(String id, String username, String idcomic, Boolean check, Date thoigian, String noidung) {
        this.id = id;
        this.username = username;
        this.idcomic = idcomic;
        this.check = check;
        this.thoigian = thoigian;
        this.noidung = noidung;
    }

    public ThongBao() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdcomic() {
        return idcomic;
    }

    public void setIdcomic(String idcomic) {
        this.idcomic = idcomic;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public Date getThoigian() {
        return thoigian;
    }

    public void setThoigian(Date thoigian) {
        this.thoigian = thoigian;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }
}
