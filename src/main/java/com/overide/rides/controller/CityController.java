package com.overide.rides.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.overide.rides.entity.City;
import com.overide.rides.service.CityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {
    /** Controller for Cities */

    @Autowired
    private CityService cityService;

    @PostMapping("/cities/create")
    public City saveCity(@RequestBody City city) {
        return cityService.saveCity(city);
    }

    @PutMapping("/cities/id/{id}")
    public City updateCity(@RequestBody City city, @PathVariable("id") Long id) {
        return cityService.updateCityById(city, id);
    }

    @GetMapping("/cities/all")
    public List<City> getCities() {
        return cityService.getCityList();
    }

    @GetMapping("/cities/id/{id}")
    public City getCityById(@PathVariable("id") Long id) {
        return cityService.getCityById(id);
    }

    @GetMapping("/cities/code/{code}")
    public City getCityByCode(@PathVariable("code") String code) {
        return cityService.getCityByCode(code.toUpperCase());
    }

    @DeleteMapping("/cities/id/{id}")
    public Map<String, String> deleteCityById(@PathVariable("id") Long id) {
        cityService.deleteCityById(id);
        Map<String, String> response = new HashMap<String, String>();
        response.put("message", "Ciudad eliminada correctamente");
        return response;
    }

}
