package com.cver.team.model.entity;

import com.cver.team.model.data.*;
import com.cver.team.model.data.string.ValueProposition;

import java.util.List;

/**
 * Created by Dimitar on 8/12/2016.
 */
public interface DocumentCard extends Entity {
    Agent getOwner();

    void setOwner(Agent owner);

    String getTitle();

    void setTitle(String title);
}
