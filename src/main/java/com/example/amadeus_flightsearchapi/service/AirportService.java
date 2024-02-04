package com.example.amadeus_flightsearchapi.service;

import com.example.amadeus_flightsearchapi.model.Airport;

import java.util.List;

public interface AirportService {
    List<Airport> findAllAirports();
    Airport saveAirport(Airport airport);
    Airport getAirportById(Long id);
    Airport updateAirport(Airport airport);
    void deleteAirport(Long id);


}