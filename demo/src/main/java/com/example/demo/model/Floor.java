package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long floorId; /**Do we really need this???*/
    private String floorNumber;
    private String building;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFloorId() { return floorId; }

    public void setFloorId(Long floorId) { this.floorId = floorId; }

    public String getBuilding() { return building; }

    public void setBuilding(String building) { this.building = building; }

    public String getFloorNumber() { return floorNumber; }

    public void setFloorNumber(String floorNumber) { this.floorNumber = floorNumber; }

}
