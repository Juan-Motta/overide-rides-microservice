package com.overide.rides.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.overide.rides.entity.Ride;
import com.overide.rides.service.RideService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RideController {
    /** Controller for rides */

    @Autowired
    private RideService rideService;

    @PostMapping("/rides/create")
    public Ride saveRide(@RequestBody Ride ride) {
        return rideService.saveRide(ride);
    }

    @PutMapping("/rides/id/{id}")
    public Ride updateRide(@RequestBody Ride ride, @PathVariable("id") Long id) {
        return rideService.updateRideById(ride, id);
    }

    @GetMapping("/rides/all")
    public List<Ride> getRides() {
        return rideService.getRideList();
    }

    @GetMapping("/rides/id/{id}")
    public Ride getRideById(@PathVariable("id") Long id) {
        return rideService.getRideById(id);
    }

    @DeleteMapping("/rides/id/{id}")
    public Map<String, String> deleteRideById(@PathVariable("id") Long id) {
        rideService.deleteRideById(id);
        Map<String, String> response = new HashMap<String, String>();
        response.put("message", "Trayecto eliminado correctamente");
        return response;
    }

}
