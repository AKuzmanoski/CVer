package com.cver.team.model.externalresource;

import java.sql.Blob;

/**
 * Created by Dimitar on 8/12/2016.
 */
public class StaticFile extends ExternalResource {
    private String contentType;
    private byte[] value;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }
}
