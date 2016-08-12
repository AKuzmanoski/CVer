package com.cver.team.model.data;

import com.cver.team.model.externalresource.Position;

/**
 * Created by Dimitar on 7/7/2016.
 */
public class WorkExperience extends Experience {

    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}

