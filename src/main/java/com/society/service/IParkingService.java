package com.society.service;

import java.util.List;

import com.society.entity.Parking;

public interface IParkingService {
    public Parking logVehicleEntry(Parking parking);
    public void logVehicleExit(String vehicleNumber);
    public List<Parking> getParkingLog();
}
