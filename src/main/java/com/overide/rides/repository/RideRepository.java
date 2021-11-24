package com.overide.rides.repository;

import com.overide.rides.entity.Ride;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    /** Defines the methods used for connecting with the db */

}
