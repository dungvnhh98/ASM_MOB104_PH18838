package com.example.asm_mob104_name.Mode;

import java.util.Date;

public class BinhLuan {
    public String idTruyen;

    public String idUser;
    public String ND;
    public Date time;
    public String fullname;

    public BinhLuan(String idTruyen, String idUser, String ND, Date time, String fullname) {
        this.idTruyen = idTruyen;
        this.idUser = idUser;
        this.ND = ND;
        this.time = time;
        this.fullname = fullname;
    }
}
