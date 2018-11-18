
package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.*;
import com.example.demo.repository.*;
import com.example.demo.nested.*;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainController {

    @Autowired
    private roomService roomService;
    @Autowired
    private floorService floorService;
    @Autowired
    private buildingService buildingService;

    @Autowired
    private clusterService clusterService;
    @Autowired
    private nodeService nodeService;
    @Autowired
    private sensorService sensorService;

    @Autowired
    private ClusterRepository clusterRepository;
    @Autowired
    private NodeRepository nodeRepository;
    @Autowired
    private SensorRepository sensorRepository;


    /**
     *
     * Add
     */

    @CrossOrigin(origins = "*")
    @PostMapping("/buildings")
    public String addBuilding(@RequestBody Building building){
        return buildingService.saveBuildingtoDB(building);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/floors")
    public String addFloor(@RequestBody Floor floor){
        return floorService.saveFloortoDB(floor);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/rooms")
    public String addRoom(@RequestBody Room room){
        return roomService.saveRoomtoDB(room);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/clusters")
    public String addCluster(@RequestBody Cluster cluster)
    {
        if (cluster.getId() == 0) {
            ClientHttpRequestFactory requestFactory = new
                    HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            String url = "http://localhost:3005/clusters";
            String result = restTemplate.postForObject(url, cluster, String.class);
            return null;
        } else {
            return clusterService.addClustertoDB(cluster);
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/nodes")
    public String addNode(@RequestBody Node node)
    {
        if(node.getId() == 0) {
            ClientHttpRequestFactory requestFactory = new
                    HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            String url = "http://localhost:3005/clusters";
            String result = restTemplate.postForObject(url, node, String.class);
            return null;
        } else {
            return nodeService.addNodetoDB(node);
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/sensors")
    public String addSensor(@RequestBody Sensor sensor)
    {
        if(sensor.getId() == 0) {
            ClientHttpRequestFactory requestFactory = new
                    HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            String url = "http://localhost:3005/clusters";
            String result = restTemplate.postForObject(url, sensor, String.class);
            return null;
        } else {
            return sensorService.addSensortoDB(sensor);
        }
    }

    /**
     *
     * search geocode
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/buildings/search/geocode")
    public Iterable<Building> searchBuildingByLa(
            @RequestParam final String latitude,
            @RequestParam final String longitude,
            @RequestParam(required = false) Integer radius) {
        return buildingService.searchBuildingByLa(latitude,longitude,radius);
    }
    public Iterable<Building> searchBuildingByCity(
            @RequestParam final String city,
            @RequestParam final String state,
            @RequestParam final String zipcode)
    {
        return buildingService.searchBuildingByCity(city,state,zipcode);
    }


    /**
     * delete
     */

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "building_id")
    public void deleteBuilding(
            @PathVariable("building_id" ) final long building_id)
    {
        buildingService.deleteBuilding(building_id);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "floor_id")
    public void deleteFloor(
            @PathVariable("floor_id" ) final long floor_id)
    {
        floorService.deleteFloor(floor_id);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "room_id")
    public void deleteRoom(
            @PathVariable("room_id") final long room_id)
    {
        roomService.deleteRoom(room_id);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/clusters/{cluster_id}")
    public void deleteCluster(
            @PathVariable("cluster_id") final long cluster_id,
            @RequestParam(value = "from", required = false) final String source)
    {
        clusterService.deleteCluster(cluster_id);
        if (source == null) {
            ClientHttpRequestFactory requestFactory = new
                    HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            String url = "http://localhost:3005/clusters/" + cluster_id;
            restTemplate.delete(url);
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/nodes/{node_id}")
    public void deleteNode(
            @PathVariable("node_id") final long node_id,
            @RequestParam(value = "from", required = false) final String source
    )
    {
        nodeService.deleteNode(node_id);
        if (source == null) {
            ClientHttpRequestFactory requestFactory = new
                    HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            String url = "http://localhost:3005/nodes/"+node_id;
            restTemplate.delete(url);
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/sensors/{sensor_id}")
    public void deleteSensor(
            @PathVariable("sensor_id") final long sensor_id,
            @RequestParam(value = "from", required = false) final String source)
    {
        sensorService.deleteSensor(sensor_id);
        if (source == null) {
            ClientHttpRequestFactory requestFactory = new
                    HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            String url = "http://localhost:3005/sensors/" + sensor_id;
            restTemplate.delete(url);
        }
    }

    /**
     * fetch_nested
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/buildings/{building_id}")
    public String getBuildingByBuildingId(
            @PathVariable final long building_id,
            Model model,
            @RequestParam(value = "fetch_nested", required = false) final String nestedContent)
    {
        buildingNested buildingNest = buildingService.getBuildingNestedByBuildingId(building_id,"floor,cluster");
        Map<Integer,Boolean> matchedRes = buildingService.getFloorCluterMatchResult(buildingNest);
        model.addAttribute("matchedRes", matchedRes);
        Cluster cluster = new Cluster();
        model.addAttribute("cluster",cluster);

        if(nestedContent==null)
            return buildingService.getBuildingByBuildingId(building_id).toString();
        else
            return buildingService.getBuildingNestedByBuildingId(building_id,nestedContent).toString();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/floors/{floor_id}")
    public String getFloorByFloorId(
            @PathVariable final long floor_id,
            Model model,
            @RequestParam(value = "fetch_nested", required = false) final String nestedContent)
    {
        floorNested floorNest = floorService.getFloorNestedByFloorId(floor_id,"room,node");

        Map<Integer,Boolean> matchedRes = floorService.getRoomNodeMatchResult(floorNest);
        model.addAttribute("matchedRes", matchedRes);
        Node node = new Node();
        model.addAttribute("node",node);

        if(nestedContent==null)
            return floorService.getFloorByFloorId(floor_id).toString();
        else
            return floorService.getFloorNestedByFloorId(floor_id,nestedContent).toString();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/rooms/{room_id}")
    public String getRoomByRoomId(
            @PathVariable final long room_id,
            @RequestParam(value = "fetch_nested",required = false) final String nestedContent)
    {
        if(nestedContent==null)
            return roomService.getRoomByRoomId(room_id).toString();
        else
            return roomService.getRoomNestedByRoomId(room_id,nestedContent).toString();

    }

    @CrossOrigin(origins = "*")
    @GetMapping("/clusters/{cluster_id}")
    public String getClusterByClusterId(
            @PathVariable final long cluster_id,
            @RequestParam(value = "fetch_nested",required = false) final String nestedContent)
    {
        if(nestedContent==null)
            return clusterService.getClusterByClusterId(cluster_id);
        else
            return clusterService.getClusterNestedByClusterId(cluster_id,nestedContent);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/nodes/{node_id}")
    public String getNodeByNodeId(
            @PathVariable final long node_id,
            @RequestParam(value = "fetch_nested",required = false) final String nestedContent)
    {
        if(nestedContent==null)
            return nodeService.getNodeByNodeId(node_id).toString();
        else
            return nodeService.getNodeNestedByNodeId(node_id,nestedContent).toString();
    }
    /**
     * Statistics
     */

    @CrossOrigin(origins = "*")
    @GetMapping("/buildings/statistics/{building_id}")
    public HashMap<String, Object> getBuildingStats(@PathVariable final long building_id)
    {
        List<Object[]> a = buildingService.countBuildingClustersAndNodes(building_id);
        HashMap<String, Object> hmap = new HashMap<String, Object>();
        Object[] count = a.get(0);
        hmap.put("cluster_count", count[0]);
        hmap.put("node_count", count[1]);
        hmap.put("sensor_count", count[2]);
        return hmap;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/floors/statistics/{floor_id}")
    public HashMap<String, Object> getFloorStats(@PathVariable final long floor_id)
    {
        List<Object[]> a = floorService.countFloorRoomNodeSensor(floor_id);
        HashMap<String, Object> hmap = new HashMap<String, Object>();
        Object[] count = a.get(0);
        hmap.put("room_count", count[0]);
        hmap.put("node_count", count[1]);
        hmap.put("sensor_count", count[2]);
        return hmap;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/rooms/statistics/{room_id}")
    public HashMap<String, Object> getRoomStats(@PathVariable final long room_id)
    {
        List<Object[]> a = roomService.countNodeSensor(room_id);
        HashMap<String, Object> hmap = new HashMap<String, Object>();
        Object[] count = a.get(0);
        hmap.put("node_count", count[0]);
        hmap.put("sensor_count", count[1]);
        return hmap;
    }

}
