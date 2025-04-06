package com.example.drone.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String>{
	Drone findDroneBySerialNumber(String serialNumber);
	Iterable<Drone> findByState(String droneState);
	@Modifying
	@Query("Update drone set state=:droneState where serial_number =:serialNumber")
	void updateDroneState(String droneState, String serialNumber);
	@Modifying
	@Query("Update drone set state=:droneState, battery_capacity=:batteryCapacity where serial_number =:serialNumber")
	void updateDroneBatteryState(String droneState, int batteryCapacity, String serialNumber);
}
