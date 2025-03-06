package cinema.service;

import cinema.entities.Cinema;
import cinema.entities.CinemaStatistics;
import cinema.entities.Seat;
import cinema.exceptions.PurchaseTicketErrorException;
import cinema.repositories.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import cinema.exceptions.InvalidPasswordException;

@Service
public class CinemaService {

    private static final String PASSWORD = "super_secret";

    private final CinemaRepository cinemaRepo;

    @Autowired
    public CinemaService(CinemaRepository seatRepo) {
        this.cinemaRepo = seatRepo;
    }

    public Cinema getCinema() {
        return new Cinema(cinemaRepo.getTotalRows(), cinemaRepo.getTotalColumns(), cinemaRepo.getAllSeats());
    }

    public Map<String, Object> purchaseTicket(Seat seatToBook) {
        if (seatToBook.getRow() <= 0 || seatToBook.getColumn() <= 0 || seatToBook.getRow() > cinemaRepo.getTotalRows()
                || seatToBook.getColumn() > cinemaRepo.getTotalColumns()) {
            throw new PurchaseTicketErrorException("The number of a row or a column is out of bounds!");
        }

        if (seatToBook.isPurchased()) {
            throw new PurchaseTicketErrorException("The ticket has been already purchased!");
        }

        Seat seat = cinemaRepo
                .getAllSeats()
                .stream()
                .filter(s -> s.getRow() == seatToBook.getRow()
                        && s.getColumn() == seatToBook.getColumn()
                        && !s.isPurchased())
                        .findAny()
                        .orElseThrow(() -> new PurchaseTicketErrorException("The ticket has been already purchased!"));

        seat.setPurchased(true);
        String token = UUID.randomUUID().toString();
        return cinemaRepo.purchaseTicket(token, seat);
    }

    public Map<String, Object> returnTicket(String token) {
        if (!cinemaRepo.existsTicket(token))
            throw new PurchaseTicketErrorException("Wrong token!");

        Seat seatToReturn = cinemaRepo.getSeat(token);

        Seat seat = cinemaRepo
                .getAllSeats()
                .stream()
                .filter(s -> s.getRow() == seatToReturn.getRow()
                        && s.getColumn() == seatToReturn.getColumn())
                .findAny()
                .orElseThrow(() -> new PurchaseTicketErrorException("Wrong token!"));

        seat.setPurchased(false);
        cinemaRepo.removeTicket(token);
        return Map.of("returned_ticket", seat);
    }

    public CinemaStatistics getStatistics(String password) {
        if (!isValidPassword(password))
            throw new InvalidPasswordException();

        return new CinemaStatistics.Builder()
                .currentIncome(cinemaRepo.getIncome())
                .numberOfAvailableSeats(cinemaRepo.getAvailableSeats())
                .numberOfPurchasedTickets(cinemaRepo.getPurchasedTickets())
                .build();
    }

    public boolean isValidPassword(String password) {
        return PASSWORD.equals(password);
    }
}