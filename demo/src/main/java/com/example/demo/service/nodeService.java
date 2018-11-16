package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.repository.*;
import com.example.demo.model.*;
import com.example.demo.nested.*;
import java.util.*;

@Service
public class nodeService {
    @Autowired
    private NodeRepository nodeRepository;
    @Autowired
    private SensorRepository sensorRepository;
    @Autowired
    private FloorRepository floorRepository;
    @Autowired
    private ClusterRepository clusterRepository;
    @Autowired
    private RoomRepository roomRepository;


    public String addNodetoDB(Node node){
        nodeRepository.save(node);
        return node.toString();
    }

    public void deleteNode(long node_id){
        Long nodeId = Long.valueOf(node_id).longValue();
        Iterable<Node> nodes = nodeRepository.findAll();
        for(Node node: nodes){
            if(nodeId == node.getId())
            nodeRepository.deleteById(node.getId());
            sensorRepository.deleteSensorByNodeId(node.getId());
        }
    }

    public Node getNodeByNodeId(long node_id){
        Long nodeId = Long.valueOf(node_id).longValue();
        return nodeRepository.findById(nodeId).get();
    }

    public nodeNested getNodeNestedByNodeId(long node_id,String requirement){
        Long nodeId = Long.valueOf(node_id).longValue();
        Node node = nodeRepository.findById(nodeId).get();

        List<Sensor> sensorList = sensorRepository.findSensorByNodeId(nodeId);

        nodeNested nodeNest = new nodeNested(node,sensorList);

        return nodeNest;
    }


}
