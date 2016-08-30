package com.cver.team.model.literal;

/**
 * Created by User on 3/2/2016.
 */
public class Identifier {
    private String id;
    private String URI;

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Identifier)) return false;

        Identifier that = (Identifier) o;

        return getURI().equals(that.getURI());

    }

    @Override
    public int hashCode() {
        return getURI().hashCode();
    }
}
