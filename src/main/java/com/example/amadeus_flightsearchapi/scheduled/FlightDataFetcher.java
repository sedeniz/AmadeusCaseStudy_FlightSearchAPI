package com.example.amadeus_flightsearchapi.scheduled;

import com.example.amadeus_flightsearchapi.model.Flight;
import com.example.amadeus_flightsearchapi.service.FlightService;
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
    private final Random random = new Random();

    public FlightDataFetcher(FlightService flightService){
        this.flightService = flightService;
    }

    // Runs every day at midnight
    //@Scheduled(cron = "0 0 0 * * ?")
    @Scheduled(cron = "0 0/5 * * * ?")
    public void fetchFlightData() {
        List<Flight> flights = generateMockFlights(10); // Generate 10 mock flights

        flights.forEach(flightService::saveFlight);
    }

    private List<Flight> generateMockFlights(int numberOfFlights) {
        List<Flight> flights = new ArrayList<>();
        for (int i = 0; i < numberOfFlights; i++) {
            Flight mockFlight = new Flight();
            String departureCity = generateRandomCity();
            String arrivalCity;

            do {
                arrivalCity = generateRandomCity(); // Ensure departure and arrival cities are not the same
            } while(arrivalCity.equals(departureCity));

            mockFlight.setDepartureAirport(departureCity);
            mockFlight.setArrivalAirport(arrivalCity);
            mockFlight.setDepartureDateTime(LocalDateTime.now().plusDays(random.nextInt(30)));
            mockFlight.setReturnDateTime(mockFlight.getDepartureDateTime().plusDays(2 + random.nextInt(10))); // Ensuring return date is after departure
            mockFlight.setPrice(new BigDecimal(100 + random.nextInt(900))); // Random price between 100 and 1000
            flights.add(mockFlight);
        }
        return flights;
    }

    private String generateRandomCity() {
        String[] cities = {"Istanbul", "Rome", "Barcelona", "New York", "Los Angeles", "Paris", "London", "Dubai", "Tokyo", "Seoul"};
        return cities[random.nextInt(cities.length)];
    }

}
