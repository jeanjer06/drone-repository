package com.example.drone.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneLoadRepository extends JpaRepository<DroneLoad, Long>{
	Iterable<DroneLoad> findbyDrone(String droneSerial);
}
