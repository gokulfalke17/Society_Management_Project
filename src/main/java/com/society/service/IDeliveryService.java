package com.society.service;

import java.util.List;

import com.society.entity.Delivery;

public interface IDeliveryService {
    public Delivery saveDelivery(Delivery delivery);
    public List<Delivery> getAllDeliveries();
}
