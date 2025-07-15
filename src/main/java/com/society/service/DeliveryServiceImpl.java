package com.society.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.entity.Delivery;
import com.society.repository.IDeliveryRepository;

@Service
public class DeliveryServiceImpl implements IDeliveryService {

    @Autowired
    private IDeliveryRepository deliveryRepo;

    @Override
    public Delivery saveDelivery(Delivery delivery) {
        delivery.setReceivedAt(LocalDateTime.now());
        return deliveryRepo.save(delivery);
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        return deliveryRepo.findAll();
    }
}
