package com.example.drone.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="DRONE_LOAD")
public class DroneLoad {
	@Column(name="DRONE_SERIAL")
    private String droneSerial;
	@Column(name="MEDICATION_CODE")
    private String medicationCode;
	@Column(name="WEIGHT")
    private int medicationWeight;
	
	public DroneLoad(String droneSerial, String medicationCode, int medicationWeight) {
		this.droneSerial = droneSerial;
        this.medicationCode = medicationCode;
        this.medicationWeight = medicationWeight;
	}
	public String getDroneSerial() {
		return droneSerial;
	}
	public String getMedicationCode() {
		return medicationCode;
	}
	public int getMedicationWeight() {
		return medicationWeight;
	}
	

}
