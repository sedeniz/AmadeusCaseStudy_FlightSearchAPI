package com.example.amadeus_flightsearchapi.repository;

import com.example.amadeus_flightsearchapi.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}

