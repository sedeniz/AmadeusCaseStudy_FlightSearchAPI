package com.example.amadeus_flightsearchapi.service.impl;

import com.example.amadeus_flightsearchapi.model.Flight;
import com.example.amadeus_flightsearchapi.repository.FlightRepository;
import com.example.amadeus_flightsearchapi.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public List<Flight> searchFlights(String departureAirport, String arrivalAirport, LocalDateTime departureDateTime, LocalDateTime returnDateTime) {

        List<Flight> flights = flightRepository.findAll();

        List<Flight> outboundFlights = flights.stream()
                .filter(flight -> flight.getDepartureAirport().equals(departureAirport)
                        && flight.getArrivalAirport().equals(arrivalAirport)
                        && flight.getDepartureDateTime().isEqual(departureDateTime))
                .toList();

        List<Flight> matchingFlights = new ArrayList<>(outboundFlights);

        if (returnDateTime != null) {
            List<Flight> returnFlights = flights.stream()
                    .filter(flight -> flight.getDepartureAirport().equals(arrivalAirport)
                            && flight.getArrivalAirport().equals(departureAirport)
                            && flight.getDepartureDateTime().isEqual(returnDateTime))
                    .toList();

            matchingFlights.addAll(returnFlights);
        }

        return matchingFlights;
    }


    @Override
    public Flight saveFlight(Flight flight) {
        flightRepository.save(flight);
        return flight;
    }

    @Override
    public List<Flight> findAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    @Override
    public Flight updateFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public void deleteAllFlights() {
        flightRepository.deleteAll();
    }
}
