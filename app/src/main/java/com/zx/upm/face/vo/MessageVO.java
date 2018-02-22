package com.zx.upm.face.vo;

public class MessageVO {

    private int id;
    private int type;
    private Double lng;
    private Double lat;
    private String content;
    private String dateTIme;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateTIme() {
        return dateTIme;
    }

    public void setDateTIme(String dateTIme) {
        this.dateTIme = dateTIme;
    }
}
