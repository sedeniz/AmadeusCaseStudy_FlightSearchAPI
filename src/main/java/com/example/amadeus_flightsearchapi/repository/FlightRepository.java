package com.example.amadeus_flightsearchapi.repository;

import com.example.amadeus_flightsearchapi.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}