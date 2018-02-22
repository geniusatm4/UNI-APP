package com.zx.upm.bo;

public class FaceSearchReq extends BaseBO {

    private Double score = 0.1;
    private Integer topNum = 10;
    private String[] dbId = new String[]{"3"};
    private String[] dbName = new String[]{"fctest"};
    private String imageBase64;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getTopNum() {
        return topNum;
    }

    public void setTopNum(Integer topNum) {
        this.topNum = topNum;
    }

    public String[] getDbId() {
        return dbId;
    }

    public void setDbId(String[] dbId) {
        this.dbId = dbId;
    }

    public String[] getDbName() {
        return dbName;
    }

    public void setDbName(String[] dbName) {
        this.dbName = dbName;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}
