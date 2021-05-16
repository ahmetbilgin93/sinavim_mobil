package com.example.mobile1;

public class sorular {
    private String soru;
    private String cevap;
    private String y1;
    private String y2;
    private String y3;

    public String getY1() {
        return y1;
    }

    public void setY1(String y1) {
        this.y1 = y1;
    }

    public String getY2() {
        return y2;
    }

    public void setY2(String y2) {
        this.y2 = y2;
    }

    public String getY3() {
        return y3;
    }

    public void setY3(String y3) {
        this.y3 = y3;
    }

    public String getY4() {
        return y4;
    }

    public void setY4(String y4) {
        this.y4 = y4;
    }

    private String y4;
    private int soruid;

    public sorular(String soru, String cevap, String y1, String y2, String y3, String y4, int soruid) {
        this.soru = soru;
        this.cevap = cevap;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.y4 = y4;
        this.soruid = soruid;
    }

    public String getSoru() {
        return soru;
    }

    public void setSoru(String soru) {
        this.soru = soru;
    }

    public String getCevap() {
        return cevap;
    }

    public void setCevap(String cevap) {
        this.cevap = cevap;
    }

    public int getSoruid() {
        return soruid;
    }

    public void setSoruid(int soruid) {
        this.soruid = soruid;
    }


}
