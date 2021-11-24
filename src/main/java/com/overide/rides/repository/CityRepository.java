package com.overide.rides.repository;

import com.overide.rides.entity.City;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    /** Defines the methods used for connecting with the db */
    @Query(nativeQuery = true, value = "SELECT * FROM t_cities WHERE code = :code")
    City findCityByCode(@Param("code") String code);

    @Query(nativeQuery = true, value = "SELECT * FROM t_cities WHERE name = :name")
    City findCityByName(@Param("name") String name);

    @Query(nativeQuery = true, value = "SELECT * FROM t_cities WHERE city_id = :id")
    City findCityById(@Param("id") Long id);
}
