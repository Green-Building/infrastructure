package com.example.demo.repository;

import com.example.demo.model.Floor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FloorRepository extends CrudRepository<Floor, Long>{

    @Transactional
    @Modifying
    @Query("delete from Floor floor where floor.building_id = :building_id")
    void deleteFloorByBuildingId(@Param("building_id")long building_id);

    @Transactional
    @Query("select floor from Floor floor where floor.building_id = :building_id")
    List<Floor> findFloorByBuildingId(@Param("building_id") long building_id);

}