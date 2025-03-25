package com.example.drone.data;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="DRONE")
public class Drone {
	@Id
	@Column(name="SERIAL_NUMBER")
	private String serialNumber;
	@Column(name="MODEL")
	private String model;
	@Column(name="WEIGHT_LIMIT")
	private int weightLimit;
	@Column(name="BATTERY_CAPACITY")
	private int batteryCapacity;
	@Column(name="STATE")
	private String state;
	@OneToMany
	private List<DroneLoad> medications = new ArrayList<>();
	
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getWeightLimit() {
		return weightLimit;
	}
	public void setWeightLimit(int weightLimit) {
		this.weightLimit = weightLimit;
	}
	public int getBatteryCapacity() {
		return batteryCapacity;
	}
	public void setBatteryCapacity(int batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
    public int getCurrentLoad() { 
    	return medications.stream().mapToInt(DroneLoad::getMedicationWeight).sum(); 
    }
    public List<DroneLoad> getMedications() { 
    	return medications; 
    }
}
