package cinema.controller;

import cinema.entities.Cinema;
import cinema.entities.CinemaStatistics;
import cinema.entities.Seat;
import cinema.service.CinemaService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CinemaController {

    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/seats")
    public Cinema getCinema() {
        return cinemaService.getCinema();
    }

    @PostMapping("/purchase")
    public Map<String, Object> purchaseTicket(@RequestBody Seat seatToBook) {
        return cinemaService.purchaseTicket(seatToBook);
    }

    @PostMapping("/return")
    public Map<String, Object> returnTicket(@RequestBody String token) {
        return cinemaService.returnTicket(token);
    }

    @GetMapping("/stats")
    public CinemaStatistics getStats(@RequestParam(required = false) String password) {
       return cinemaService.getStatistics(password);
    }
}