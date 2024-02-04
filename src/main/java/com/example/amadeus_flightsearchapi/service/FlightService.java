package com.example.amadeus_flightsearchapi.service;

import com.example.amadeus_flightsearchapi.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {
    List<Flight> searchFlights(String departureAirport, String arrivalAirport, LocalDateTime departureDateTime, LocalDateTime returnDateTime);

    Flight saveFlight(Flight flight);

    List<Flight> findAllFlights();

    Flight getFlightById(Long id);
    Flight updateFlight(Flight flight);
    void deleteFlight(Long id);
    void deleteAllFlights();
}
