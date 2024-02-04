package com.example.amadeus_flightsearchapi.service;

import com.example.amadeus_flightsearchapi.model.Airport;

import java.util.List;

public interface AirportService {
    List<Airport> findAllAirports();
}