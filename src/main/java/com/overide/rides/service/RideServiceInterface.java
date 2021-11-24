package com.overide.rides.service;

import java.util.List;

import com.overide.rides.entity.Ride;

public interface RideServiceInterface {
    /** Defines methos used for service */

    public Ride saveRide(Ride ride);

    public List<Ride> getRideList();

    public Ride getRideById(Long id);

    public void deleteRideById(Long id);

    public Ride updateRideById(Ride ride, Long id);
}
