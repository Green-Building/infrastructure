package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.repository.*;
import com.example.demo.model.*;

@Service
public class sensorService {
    @Autowired
    private SensorRepository sensorRepository;

    public String addSensortoDB(Sensor sensor){
        sensorRepository.save(sensor);
        return sensor.toString();
    }

    public void deleteSensor(long sensor_id){
        Long sensorId = Long.valueOf(sensor_id).longValue();
        Iterable<Sensor> sensors = sensorRepository.findAll();
        for(Sensor sensor: sensors){
            if(sensorId == sensor.getId())
                sensorRepository.deleteById(sensorId);
        }
    }

}
