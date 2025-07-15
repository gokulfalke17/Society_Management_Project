/*package com.society.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.society.entity.Parking;

@Repository
public interface IParkingRepository extends JpaRepository<Parking, Integer> {
	@Query("SELECT p FROM Parking p WHERE p.vehicleNumber = :vehicleNumber AND p.exitTime IS NULL ORDER BY p.entryTime DESC")
	public Optional<Parking> vehicleExit(@Param("vehicleNumber") String vehicleNumber);

}
*/

package com.society.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.society.entity.Parking;

@Repository
public interface IParkingRepository extends JpaRepository<Parking, Integer> {

	public Optional<Parking> findTopByVehicleNumberAndExitTimeIsNullOrderByEntryTimeDesc(String vehicleNumber);

}
