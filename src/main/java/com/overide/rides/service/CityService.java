package com.overide.rides.service;

import java.util.List;

import com.overide.rides.entity.City;
import com.overide.rides.exception.ApiRequestException;
import com.overide.rides.repository.CityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService implements CityServiceInterface {
    /** Implements methods to connect controllers with repository */

    @Autowired
    private CityRepository cityRepository;

    @Override
    public City saveCity(City city) {
        if (cityRepository.findCityByCode(city.getCode()) == null
                && cityRepository.findCityByName(city.getName()) == null) {
            return cityRepository.save(city);
        } else {
            throw new ApiRequestException("El nombre o codigo del registro no puede estar repetido");
        }
    }

    @Override
    public List<City> getCityList() {
        return cityRepository.findAll();
    }

    @Override
    public City getCityById(Long id) {
        return cityRepository.findById(id).get();
    }

    @Override
    public void deleteCityById(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public City getCityByCode(String code) {
        return cityRepository.findCityByCode(code);
    }

    @Override
    public City getCityByName(String name) {
        return cityRepository.findCityByName(name);
    }

    @Override
    public City updateCityById(City city, Long id) {
        City cityFromDb = cityRepository.findById(id).get();
        if (city.getCode() != null && city.getName() != null) {
            cityFromDb.setName(city.getName());
            cityFromDb.setCode(city.getCode());
            return cityRepository.save(cityFromDb);
        } else if (city.getName() != null) {
            cityFromDb.setName(city.getName());
            return cityRepository.save(cityFromDb);
        } else if (city.getCode() != null) {
            cityFromDb.setCode(city.getCode());
            return cityRepository.save(cityFromDb);
        } else {
            throw new ApiRequestException("Los campos ingresados deben ser validos");
        }
    }

}
