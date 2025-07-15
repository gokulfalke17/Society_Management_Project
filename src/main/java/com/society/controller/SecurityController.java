package com.society.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.society.entity.Delivery;
import com.society.entity.Parking;
import com.society.entity.Visitor;
import com.society.service.IDeliveryService;
import com.society.service.IParkingService;
import com.society.service.IVisitorService;

@Controller
@RequestMapping("/security")
public class SecurityController {

    @Autowired
    private IVisitorService visitorService;

    @Autowired
    private IDeliveryService deliveryService;

    @Autowired
    private IParkingService parkingService;

    // ---------------------- Visitor ----------------------

    @GetMapping("/visitors/add")
    public String showAddVisitorForm(Model model) {
        model.addAttribute("visitor", new Visitor());
        return "add-visitor";
    }

    @PostMapping("/visitors/save")
    public String saveVisitor(@RequestParam("memberName") String memberName, @ModelAttribute Visitor visitor) {
        visitorService.addVisitor(memberName, visitor);
        return "redirect:/security/visitors/current";
    }

    @GetMapping("/visitors/current")
    public String showCurrentVisitors(Model model) {
        model.addAttribute("visitors", visitorService.getCurrentVisitors());
        return "current-visitors";
    }

    @GetMapping("/visitors/history")
    public String showVisitorHistory(Model model) {
        model.addAttribute("visitors", visitorService.getVisitorHistory());
        return "visitor-history";
    }

    @GetMapping("/visitors/member/{memberId}")
    public String visitorsByMember(@PathVariable("memberId") Integer memberId, Model model) {
        model.addAttribute("visitors", visitorService.getVisitorByMember(memberId));
        return "member-visitors";
    }

    @GetMapping("/visitors/checkout/{visitorId}")
    public String checkoutVisitor(@PathVariable("visitorId") Integer visitorId) {
        visitorService.checkoutVisitor(visitorId);
        return "redirect:/security/visitors/current";
    }

    // ---------------------- Delivery ----------------------

    @GetMapping("/deliveries/add")
    public String showAddDeliveryForm(Model model) {
        model.addAttribute("delivery", new Delivery());
        return "add-delivery";
    }

    @PostMapping("/deliveries/save")
    public String saveDelivery(@ModelAttribute Delivery delivery) {
        deliveryService.saveDelivery(delivery);
        return "redirect:/security/deliveries";
    }

    @GetMapping("/deliveries")
    public String showAllDeliveries(Model model) {
        model.addAttribute("deliveries", deliveryService.getAllDeliveries());
        return "deliveries";
    }

    // ---------------------- Parking ----------------------

    @GetMapping("/parking/entry")
    public String showParkingEntryForm(Model model){
    	System.out.println("Calling...");
        model.addAttribute("parking", new Parking());
        return "vehicle-entry";
    }

    @PostMapping("/parking/entry")
    public String saveParkingEntry(@ModelAttribute Parking parking) {
        parking.setEntryTime(LocalDateTime.now());
        parkingService.logVehicleEntry(parking);
        return "redirect:/security/parking";
    }

    @GetMapping("/parking/exit")
    public String showParkingExitForm(Model model) {
        model.addAttribute("parking", new Parking());
        return "vehicle-exit";
    }

    @PostMapping("/parking/exit")
    public String processParkingExit(@RequestParam("vehicleNumber") String vehicleNumber) {
        parkingService.logVehicleExit(vehicleNumber);
        return "redirect:/security/parking";
    }

    @GetMapping("/parking")
    public String showParkingLog(Model model) {
        model.addAttribute("parkingLog", parkingService.getParkingLog());
        return "parking-log";
    }

   
}
