package com.zx.upm.face.vo;

import java.util.List;

public class FacePassTrajectory extends BaseVO {
    private Integer totalCount;
    private Integer beginIndex;
    private Integer endIndex;
    private Long timestamp;
    private List<FacePassItem> facePassItems;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(Integer beginIndex) {
        this.beginIndex = beginIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public List<FacePassItem> getFacePassItems() {
        return facePassItems;
    }

    public void setFacePassItems(List<FacePassItem> facePassItems) {
        this.facePassItems = facePassItems;
    }
}
