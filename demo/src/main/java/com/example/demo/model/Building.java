package com.example.demo.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonPropertyOrder({
        "id",
        "image_url",
        "address",
        "city",
        "state",
        "zipcode",
        "num_of_floors",
        "longitude",
        "latitude"
})

@Entity
@Table(name = "building")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long image_url;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String num_of_floors;
    private Double longitude;
    private Double latitude;


    public long  getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getImage_url() {
        return image_url;
    }

    public void setImage_url(long image_url) {
        this.image_url = image_url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getNum_of_floors() {
        return num_of_floors;
    }

    public void setNum_of_floors(String num_of_floors) {
        this.num_of_floors = num_of_floors;
    }

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

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

}