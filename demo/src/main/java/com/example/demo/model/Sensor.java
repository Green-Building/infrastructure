package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sensor_id;
    private String name;
    private String type;
    private Date install_time;
    private String status;

    @ManyToOne
    private Node node;
    private Cluster cluster;

    public Long getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(Long sensor_id) {
        this.sensor_id = sensor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getInstall_time() {
        return install_time;
    }

    public void setInstall_time(Date install_time) {
        this.install_time = install_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
}

