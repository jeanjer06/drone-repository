package com.example.drone.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.drone.data.Drone;
import com.example.drone.data.DroneLoad;
import com.example.drone.data.Medication;

@RestController
@RequestMapping("/api/drones")
public class DroneController {
    private static final Map<String, Drone> drones = new HashMap<>();
    private static final Map<String, Integer> modelCapacity = Map.of(
            "LIGHTWEIGHT", 500,
            "MIDDLEWEIGHT", 750,
            "CRUISERWEIGHT", 900,
            "HEAVYWEIGHT", 1000
    );
    
    private static final List<DroneLoad> droneLoads = new ArrayList<>();
    
    @PostMapping("/register")
    public String registerDrone(@RequestBody Drone drone) {
        if (drone.getWeightLimit() > modelCapacity.get(drone.getModel())) {
            return "Weight limit exceeded.";
        }
        drones.put(drone.getSerialNumber(), drone);
        return "Drone registered successfully.";
    }
    
    @PostMapping("/load/{serialNumber}")
    public String loadDrone(@PathVariable String serialNumber, @RequestBody Medication medication) {
        Drone drone = drones.get(serialNumber);
        if (drone == null) {
            return "Drone not found.";
        }
        if (drone.getBatteryCapacity() < 25) {
            return "Battery too low to load.";
        }
        if (!drone.getState().equals("IDLE") && !drone.getState().equals("LOADING")) {
            return "Drone is not available for loading.";
        }
        if (drone.getCurrentLoad() + medication.getWeight() > drone.getWeightLimit()) {
            return "Exceeds weight limit.";
        }
        droneLoads.add(new DroneLoad(serialNumber, medication.getCode(), medication.getWeight()));
        drone.setState("LOADING");
        return "Medication loaded successfully.";
    }
    
    @GetMapping("/medications/{serialNumber}")
    public List<DroneLoad> getMedications(@PathVariable String serialNumber) {
        Drone drone = drones.get(serialNumber);
        if (drone == null) {
        	return Collections.emptyList();
        }
        return drone.getMedications(); 
    }
    
    @GetMapping("/available")
    public List<String> getAvailableDrones() {
        return drones.values().stream()
                .filter(d -> d.getState().equals("IDLE"))
                .map(Drone::getSerialNumber)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/battery/{serialNumber}")
    public String getBatteryLevel(@PathVariable String serialNumber) {
        Drone drone = drones.get(serialNumber);
        if (drone == null) {
        	return ("Drone not found.");
        }
        return "Battery: " + drone.getBatteryCapacity() + "%";
    }
    
    @Scheduled(fixedRate = 60000)
    public void updateDroneStates() {
        drones.values().forEach(drone -> {
            if (drone.getState().equals("DELIVERING")) {
                drone.setState("DELIVERED");
                drone.setBatteryCapacity(Math.max(drone.getBatteryCapacity() - 10, 0));
            } else if (drone.getState().equals("DELIVERED")) {
                drone.setState("RETURNING");
            } else if (drone.getState().equals("RETURNING")) {
                drone.setState("IDLE");
            }
        });
    }
}
