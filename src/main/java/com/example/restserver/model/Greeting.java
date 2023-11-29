package com.example.restserver.model;

public class Greeting {
    private long num;
    private String txt;
    public Greeting(long num, String txt) {
        this.num=num;
        this.txt=txt;
    }

    public long getNum() {
        return num;
    }

    public String getTxt() {
        return txt;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
