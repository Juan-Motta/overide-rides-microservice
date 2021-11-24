package com.overide.rides.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Entity
@Table(name = "t_rides")
public class Ride {
    /** Entity models for rides */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "from_city_id", referencedColumnName = "city_id")
    private City from;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "to_city_id", referencedColumnName = "city_id")
    private City to;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
    private String departure_date;

    @JsonFormat(pattern = "HH:mm", shape = Shape.STRING)
    private String departure_time;

    private Integer passengers;

    private Integer price;

    public Ride() {
    }

    public Ride(Long id, City from, City to, String departure_date, String departure_time, Integer passengers,
            Integer price) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.departure_date = departure_date;
        this.departure_time = departure_time;
        this.passengers = passengers;
        this.price = price;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getFrom() {
        return this.from;
    }

    public void setFrom(City from) {
        this.from = from;
    }

    public City getTo() {
        return this.to;
    }

    public void setTo(City to) {
        this.to = to;
    }

    public String getDeparture_date() {
        return this.departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public String getDeparture_time() {
        return this.departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public Integer getPassengers() {
        return this.passengers;
    }

    public void setPassengers(Integer passengers) {
        this.passengers = passengers;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", from='" + getFrom() + "'" + ", to='" + getTo() + "'"
                + ", departure_date='" + getDeparture_date() + "'" + ", departure_time='" + getDeparture_time() + "'"
                + ", passengers='" + getPassengers() + "'" + ", price='" + getPrice() + "'" + "}";
    }

}
