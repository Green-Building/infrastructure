package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long floor_id;
    private String floor_number;


    protected Floor(){

    }

    public Floor(long floor_id, String floor_number){
        this.floor_id = floor_id;
        this.floor_number = floor_number;
    }

    public Long getFloor_id() {
        return floor_id;
    }

    public void setFloor_id(Long floor_id) {
        this.floor_id = floor_id;
    }

    public String getFloor_number() {
        return floor_number;
    }

    public void setFloor_number(String floor_number) {
        this.floor_number = floor_number;
    }



}
