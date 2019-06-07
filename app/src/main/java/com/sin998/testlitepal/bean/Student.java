package com.sin998.testlitepal.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class Student extends LitePalSupport {

    /*
    注意：id字段可写可不写，因为不管你的实体类有没有这个字段，LitePal
    都会默认创建一个id字段（若实体类中有则不会创建），因为每个表都必须要
    一个主键，作为此表的索引，id字段即为索引。
    */

//    @Column(nullable = true)
    private int id;
//    @Column(ignore = false)
    private String pname;
//    @Column(unique = false)
    private int psex;
    @Column(defaultValue = "60.00")
    private double pscore;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getPsex() {
        return psex;
    }

    public void setPsex(int psex) {
        this.psex = psex;
    }

    public double getPscore() {
        return pscore;
    }

    public void setPscore(double pscore) {
        this.pscore = pscore;
    }
}
