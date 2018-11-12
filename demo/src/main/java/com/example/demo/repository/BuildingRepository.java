package com.example.demo.repository;

import com.example.demo.model.Building;
import org.springframework.data.repository.CrudRepository;


public interface BuildingRepository extends CrudRepository<Building, Long> {
    Building findBuildingByAddress(String address);
}
