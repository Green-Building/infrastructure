package com.example.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cluster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cluster_id;

    public Long getCluster_id() {
        return cluster_id;
    }

    public void setCluster_id(Long cluster_id) {
        this.cluster_id = cluster_id;
    }
}
