package com.cver.team.model.data;

import com.cver.team.model.data.Media;

/**
 * Created by Dimitar on 7/24/2016.
 */
public class Image extends Media {

    private String contentyType;

    private String imageURL;

    private byte[] imageData;

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getContentyType() {
        return contentyType;
    }

    public void setContentyType(String contentyType) {
        this.contentyType = contentyType;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
