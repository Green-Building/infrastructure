package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long roomId;
    private String floor;
    private String building;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoomId() { return roomId; }

    public void setRoomId(Long roomId) { this.roomId = roomId; }

    public String getFloor() { return floor; }

    public void setFloor(String floor) { this.floor = floor; }

    public String getBuilding() { return building; }

    public void setBuilding(String building) { this.building = building; }



}
