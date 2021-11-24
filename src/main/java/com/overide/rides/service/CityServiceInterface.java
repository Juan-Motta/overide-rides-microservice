package com.overide.rides.service;

import java.util.List;

import com.overide.rides.entity.City;

public interface CityServiceInterface {
    /** Defines methos used for service */

    public City saveCity(City city);

    public List<City> getCityList();

    public City getCityById(Long id);

    public void deleteCityById(Long id);

    public City getCityByCode(String code);

    public City getCityByName(String name);

    public City updateCityById(City city, Long id);

}
