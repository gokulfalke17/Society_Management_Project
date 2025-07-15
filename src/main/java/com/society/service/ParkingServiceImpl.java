package com.society.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.society.entity.Parking;
import com.society.repository.IParkingRepository;

@Service
public class ParkingServiceImpl implements IParkingService {

	@Autowired
	private IParkingRepository parkingRepo;

	@Override
	public Parking logVehicleEntry(Parking parking) {
		parking.setEntryTime(LocalDateTime.now());
		return parkingRepo.save(parking);
	}

	@Override
	public void logVehicleExit(String vehicleNumber) {
		Parking parking = parkingRepo.findTopByVehicleNumberAndExitTimeIsNullOrderByEntryTimeDesc(vehicleNumber)
				.orElseThrow(() -> new RuntimeException("No Active entry found.!"));
		parking.setExitTime(LocalDateTime.now());
		parkingRepo.save(parking);
	}

	@Override
	public List<Parking> getParkingLog() {
		return parkingRepo.findAll(Sort.by(Sort.Direction.DESC, "entryTime"));
	}
}
