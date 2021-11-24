package com.overide.rides.service;

import java.util.List;

import com.overide.rides.entity.City;
import com.overide.rides.entity.Ride;
import com.overide.rides.exception.ApiRequestException;
import com.overide.rides.repository.CityRepository;
import com.overide.rides.repository.RideRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RideService implements RideServiceInterface {
    /** Implements methods to connect controllers with repository */

    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private CityRepository cityRepository;

    @Override
    public Ride getRideById(Long id) {
        return rideRepository.findById(id).get();
    }

    @Override
    public List<Ride> getRideList() {
        return rideRepository.findAll();
    }

    @Override
    public Ride saveRide(Ride ride) {
        // get from and to city code from request
        String city_from_code = ride.getFrom().getCode();
        String city_to_code = ride.getTo().getCode();
        // verifies if the cities exist in the db and if data is valid
        if (this.validateCitiesExistence(city_from_code, city_to_code) && this.rideValidations(ride)) {
            // sets from city and to city into the instance of rides and saves it into the
            // db
            ride.setFrom(cityRepository.findCityByCode(city_from_code));
            ride.setTo(cityRepository.findCityByCode(city_to_code));
            return rideRepository.save(ride);
        } else {
            throw new ApiRequestException("La ciudad especificada no existe en la base de datos");
        }
    }

    @Override
    public Ride updateRideById(Ride ride, Long id) {
        // Obtains data from db
        Ride rideFromDb = rideRepository.findById(id).get();
        // Validations
        if (ride.getFrom() != null) {
            rideFromDb.setFrom(ride.getFrom());
        }
        if (ride.getTo() != null) {
            rideFromDb.setTo(ride.getTo());
        }
        if (ride.getDeparture_date() != null && ride.getDeparture_date().split("-").length == 3) {
            rideFromDb.setDeparture_date(ride.getDeparture_date());
        }
        if (ride.getDeparture_time() != null && ride.getDeparture_time().split(":").length == 2) {
            rideFromDb.setDeparture_time(ride.getDeparture_time());
        }
        if (ride.getPassengers() != null && ride.getPassengers() > 0) {
            rideFromDb.setPassengers(ride.getPassengers());
        }
        if (ride.getPrice() != null && ride.getPrice() > 0) {
            rideFromDb.setPrice(ride.getPrice());
        }
        return this.saveRide(rideFromDb);
    }

    @Override
    public void deleteRideById(Long id) {
        rideRepository.deleteById(id);

    }

    private boolean validateCitiesExistence(String city_from_code, String city_to_code) {
        // verifies if the cities exist in the db
        return cityRepository.findCityByCode(city_from_code) != null
                && cityRepository.findCityByCode(city_to_code) != null;
    }

    private boolean rideValidations(Ride ride) {
        // Validates the data obtained from request
        City to = ride.getTo();
        City from = ride.getFrom();
        String departure_date = ride.getDeparture_date();
        String departure_time = ride.getDeparture_time();
        Integer passengers = ride.getPassengers();
        Integer price = ride.getPrice();

        // Not null validation
        if (to != null && from != null && departure_date != null && departure_time != null && passengers != null
                && price != null) {
            // Date and time format validation
            if (departure_date.split("-").length == 3 && departure_time.split(":").length == 2) {
                // Passenger and price number validation
                if (passengers > 0 && price > 0) {
                    return true;
                } else {
                    throw new ApiRequestException("Los campos de pasajeros y precio deben ser validos");
                }
            } else {
                throw new ApiRequestException("Los campos de hora y/o fecha deben ser validos ");
            }
        } else {
            throw new ApiRequestException("Los campos no pueden estar vacios");
        }
    }

}
