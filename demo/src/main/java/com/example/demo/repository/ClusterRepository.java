package com.example.demo.repository;

import com.example.demo.model.Cluster;
import com.example.demo.model.Floor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClusterRepository extends CrudRepository<Cluster, Long> {

    @Transactional
    @Query("select cluster from Cluster cluster where cluster.building_id = :building_id")
    List<Cluster> findClusterByBuildingId(@Param("building_id") long building_id);

}