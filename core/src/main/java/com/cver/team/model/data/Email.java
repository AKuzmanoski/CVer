package com.cver.team.model.data;

import com.cver.team.model.data.Data;

/**
 * Created by Dimitar on 8/12/2016.
 */
public class Email extends Data {

    private String hashedMbox;

    private String mbox;

    public String getHashedMbox() {
        return hashedMbox;
    }

    public void setHashedMbox(String hashedMbox) {
        this.hashedMbox = hashedMbox;
    }

    public String getMbox() {
        return mbox;
    }

    public void setMbox(String mbox) {
        this.mbox = mbox;
    }


}
