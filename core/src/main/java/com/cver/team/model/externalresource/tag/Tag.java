package com.cver.team.model.externalresource.tag;

import com.cver.team.model.externalresource.ExternalResource;

/**
 * Created by Dimitar on 8/12/2016.
 */
public class Tag extends ExternalResource {

    private boolean isPublic;

    private String val;

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
