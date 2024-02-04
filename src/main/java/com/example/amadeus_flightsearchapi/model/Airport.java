package com.example.amadeus_flightsearchapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String city;

    public Airport() {}

    public Airport(String city) {
        this.city = city;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
