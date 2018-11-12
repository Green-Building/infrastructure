package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long room_id;
    private Long floor_id;
    private Long building_id;
    private String room_number;

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public Long getFloor_id() {
        return floor_id;
    }

    public void setFloor_id(Long floor_id) {
        this.floor_id = floor_id;
    }

    public Long getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(Long building_id) {
        this.building_id = building_id;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

}
