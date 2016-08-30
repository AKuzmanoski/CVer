package com.cver.team.model.externalresource;

import com.cver.team.model.BaseEntityImpl;

/**
 * Created by Dimitar on 8/12/2016.
 */
public abstract class ExternalResource extends BaseEntityImpl {
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
