package com.cver.team.model.entity;


import com.cver.team.model.tag.TemplateTag;

/**
 * Created by Dimitar on 2/28/2016.
 */
public class Template extends Entity {

    private String url;

    private String value;

    private TemplateTag templateTag;

    public TemplateTag getTemplateTag() {
        return templateTag;
    }

    public void setTemplateTag(TemplateTag templateTag) {
        this.templateTag = templateTag;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
