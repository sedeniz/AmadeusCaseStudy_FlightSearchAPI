package com.example.amadeus_flightsearchapi.service.impl;

import com.example.amadeus_flightsearchapi.model.Airport;
import com.example.amadeus_flightsearchapi.repository.AirportRepository;
import com.example.amadeus_flightsearchapi.service.AirportService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    @PostConstruct
    private void initAirports() {
        List<String> cities = Arrays.asList("Istanbul", "Madrid", "New York", "Rome", "Berlin");
        cities.forEach(this::addAirportIfMissing);
    }

    private void addAirportIfMissing(String city) {
        if (!airportRepository.existsByCity(city)) {
            Airport airport = new Airport(city);
            airportRepository.save(airport);
        }
    }

    @Autowired
    public AirportServiceImpl(AirportRepository airportRepository) {

        this.airportRepository = airportRepository;
    }

    @Override
    public Airport saveAirport(Airport airport) {

        return airportRepository.save(airport);
    }

    @Override
    public Airport getAirportById(Long id) {

        return airportRepository.findById(id).orElse(null);
    }

    @Override
    public Airport updateAirport(Airport airport) {

        return airportRepository.save(airport);
    }

    @Override
    public void deleteAirport(Long id) {

        airportRepository.deleteById(id);
    }

    @Override
    public List<Airport> findAllAirports() {

        return airportRepository.findAll();
    }

}
