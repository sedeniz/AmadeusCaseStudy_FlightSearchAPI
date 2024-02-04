package com.example.amadeus_flightsearchapi.scheduled;

import com.example.amadeus_flightsearchapi.model.Airport;
import com.example.amadeus_flightsearchapi.model.Flight;
import com.example.amadeus_flightsearchapi.service.AirportService;
import com.example.amadeus_flightsearchapi.service.FlightService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class FlightDataFetcher {

    private final FlightService flightService;
    private final AirportService airportService;
    private final Random random = new Random();

    @Autowired
    public FlightDataFetcher(FlightService flightService, AirportService airportService) {
        this.flightService = flightService;
        this.airportService = airportService;
    }

    private static final String[] cities = {"Istanbul", "Madrid", "New York", "Rome", "Berlin"};
    private static final int WEEKS = 4; // Number of cycles

    //@Scheduled(cron = "0 0 * * * ?") // This task runs everyday at midnight
    @PostConstruct
    public void fetchFlights() {
        List<Airport> airports = airportService.findAllAirports();

        if (airports.size() < 2) {
            System.out.println("You need at least two airports to generate flights.");
            return;
        }

        flightService.deleteAllFlights();
        LocalDateTime now = LocalDateTime.now();

        for (int i = 0; i < airports.size(); i++) {
            for (int j = 0; j < airports.size(); j++) {
                if (i == j) continue; // Skip flights to the same airport

                String departureCity = airports.get(i).getCity();
                String arrivalCity = airports.get(j).getCity();

                LocalDateTime departureDateTime = now.plusDays(7 + random.nextInt(8));  // Start 7-14 days from now for the first flight

                for (int week = 0; week < WEEKS; week++) {
                    BigDecimal price = BigDecimal.valueOf(500 + random.nextInt(1001)); // Random price between 500 and 1500

                    LocalDateTime returnDateTime = departureDateTime.plusWeeks(1);
                    Flight outboundFlight = new Flight(departureCity, arrivalCity, departureDateTime, week < WEEKS - 1 ? returnDateTime : null, price);
                    flightService.saveFlight(outboundFlight);

                    if (week < WEEKS - 1) {
                        // Adjust price for the return flight, ensuring variability
                        BigDecimal returnPrice = BigDecimal.valueOf(500 + random.nextInt(1001));
                        Flight returnFlight = new Flight(arrivalCity, departureCity, returnDateTime, returnDateTime.plusWeeks(1), returnPrice);
                        flightService.saveFlight(returnFlight);
                    }

                    // Prepare the departure date for the next cycle
                    departureDateTime = returnDateTime; // Next flight starts on the return date of the current flight
                }
            }
        }
    }
}
