package com.cver.team.model.data;

/**
 * Created by Dimitar on 8/12/2016.
 */
public class Video extends Data {

    private String contentyType;

    private String videoURL;

    private byte[] videoData;

    public String getContentyType() {
        return contentyType;
    }

    public void setContentyType(String contentyType) {
        this.contentyType = contentyType;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public byte[] getVideoData() {
        return videoData;
    }

    public void setVideoData(byte[] videoData) {
        this.videoData = videoData;
    }
}
