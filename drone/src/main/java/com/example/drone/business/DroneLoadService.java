package com.example.drone.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.drone.data.Drone;
import com.example.drone.data.DroneLoad;
import com.example.drone.data.DroneLoadRepository;
import com.example.drone.data.DroneRepository;
import com.example.drone.data.MedicationRepository;

@Service
public class DroneLoadService {
	private final DroneRepository droneRepository;
	private final DroneLoadRepository droneLoadRepository;
	private final MedicationRepository medicationRepository;
	
	public DroneLoadService(DroneRepository droneRepository, DroneLoadRepository droneLoadRepository, MedicationRepository medicationRepository) {
		this.droneRepository = droneRepository;
		this.droneLoadRepository = droneLoadRepository;
		this.medicationRepository = medicationRepository;
	}
	
	public void addDroneLoad(DroneLoad droneLoad) {
		if (null == droneLoad) {
			throw new RuntimeException("Drone load information cannot be null");
		}
		this.droneLoadRepository.save(droneLoad);
	}
	
	public List<DroneLoad> getLoad(Drone drone) {
		Iterable<DroneLoad> droneLoad = this.droneLoadRepository.findbyDrone(drone.getSerialNumber());
		List<DroneLoad> medicationList = new ArrayList<>();
		droneLoad.forEach(load->{medicationList.add(load);});
		return medicationList;
	}
	
	public List<Drone> getAvailableDrone() {
		Iterable<Drone> drone = this.droneRepository.findByState("IDLE");
		List<Drone> droneList = new ArrayList<>();
		drone.forEach(drones->{droneList.add(drones);});
		return droneList;
	}
	
	public Drone getDrone(String serialNumber) {
		Drone drone = this.droneRepository.findDroneBySerialNumber(serialNumber);
		return drone;
	}
	
	public void addDrone(Drone drone) {
		this.droneRepository.save(drone);
	}
	
	public void updateDroneState(String state, String serialNumber) {
		this.droneRepository.updateDroneState(state, serialNumber);
	}
	
	public void updateBatteryState(String state, int batteryCapacity, String serialNumber) {
		this.droneRepository.updateDroneBatteryState(state, batteryCapacity, serialNumber);
	}
}
