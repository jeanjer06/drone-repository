package com.example.drone.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, String>{
	Iterable<Drone> findDroneByCode(String medicationCode);
}
