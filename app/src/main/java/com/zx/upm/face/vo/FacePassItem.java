package com.zx.upm.face.vo;

public class FacePassItem {

    private int id;
    private Double lng;
    private Double lat;
    private String tollgateName;
    private String tollgateId;
    private String passTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTollgateName() {
        return tollgateName;
    }

    public void setTollgateName(String tollgateName) {
        this.tollgateName = tollgateName;
    }

    public String getTollgateId() {
        return tollgateId;
    }

    public void setTollgateId(String tollgateId) {
        this.tollgateId = tollgateId;
    }

    public String getPassTime() {
        return passTime;
    }

    public void setPassTime(String passTime) {
        this.passTime = passTime;
    }
}
