package com.cver.team.model.data;

import com.fasterxml.jackson.annotation.JsonSubTypes;

/**
 * Created by Dimitar on 8/12/2016.
 */
@JsonSubTypes({
        @JsonSubTypes.Type(value = Address.class),
        @JsonSubTypes.Type(value = Country.class),
        @JsonSubTypes.Type(value = City.class),
})
public class Location extends Data {

    private Double longitude;

    private Double latitude;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

}
