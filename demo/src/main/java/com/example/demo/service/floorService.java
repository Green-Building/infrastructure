package com.example.demo.service;


import org.springframework.stereotype.Service;
import com.example.demo.repository.*;
import com.example.demo.model.*;
import com.example.demo.nested.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class floorService {

    @Autowired
    private FloorRepository floorRepository;
    @Autowired
    private ClusterRepository clusterRepository;
    @Autowired
    private NodeRepository nodeRepository;
    @Autowired
    private SensorRepository sensorRepository;
    @Autowired
    private RoomRepository roomRepository;

    public String saveFloortoDB(Floor floor) {
        floorRepository.save(floor);
        return floor.toString();
    }

    public void deleteFloor(long floor_id){
        Long floorId = Long.valueOf(floor_id).longValue();

        Iterable<Floor> floors = floorRepository.findAll();

        for(Floor floor: floors){
            if(floorId == floor.getId()){
                floorRepository.deleteById(floor.getId());
                roomRepository.deleteRoomByFloorId(floor.getId());
            }
        }
    }

    public Floor getFloorByFloorId(long floor_id){
        Long floorId = Long.valueOf(floor_id).longValue();
        return floorRepository.findById(floorId).get();
    }

    public floorNested getFloorNestedByFloorId(long floor_id, String requirement){
        Long floorId = Long.valueOf(floor_id).longValue();
        Floor floor = floorRepository.findById(floorId).get();
        Room room = roomRepository.findById(floorId).get();

        List<Room> roomList = roomRepository.findRoomByFloorId(floorId);
        List<Node> nodeList = nodeRepository.findNodeByRoomId(room.getId());

        floorNested floorNest = new floorNested(floor,roomList,nodeList);

        return floorNest;
    }

    public Map<Integer, Boolean> getRoomNodeMatchResult(floorNested floorNest) {

        Map<Integer, Boolean> res = new HashMap<>();

        Iterable<Room> rooms = floorNest.getRooms();
        for(Room room: rooms) {
            Iterable<Node> nodes = floorNest.getNodes();
            for(Node node: nodes) {
                if(node.getRoom_id() == node.getId()) {
                    res.put(room.getRoom_number(), true);
                }
            }
            if (!res.containsKey(room.getRoom_number())) {
                res.put(room.getRoom_number(), false);
            }
        }
        return res;
    }

}