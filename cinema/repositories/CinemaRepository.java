package cinema.repositories;

import cinema.entities.Seat;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CinemaRepository {
    private static final int NB_ROWS = 9;
    private static final int NB_COLUMNS = 9;
    private static final int PREMIUM_PRICE = 10;
    private static final int REGULAR_PRICE = 8;
    private static final Map<String, Seat> TICKETS;
    private static final List<Seat> SEATS;

    static {
        TICKETS = new HashMap<>();
        SEATS = new ArrayList<>();
        for (int row = 1; row <= NB_ROWS; row++) {
            for (int column = 1; column <= NB_COLUMNS; column++) {
                SEATS.add(new Seat(
                                row, column,
                                row <= 4 ? PREMIUM_PRICE : REGULAR_PRICE
                                , true
                        )
                );
            }
        }
    }

    public int getTotalRows() {
        return NB_ROWS;
    }

    public int getTotalColumns() {
        return NB_COLUMNS;
    }

    public List<Seat> getAllSeats() {
        return SEATS;
    }

    public Seat getSeat(String token) {
        return TICKETS.get(token);
    }

    public Map<String, Object> purchaseTicket(String token, Seat seat) {
        TICKETS.put(token, seat);
        return Map.of("token", token, "ticket", seat);
    }

    public void removeTicket(String token) {
        TICKETS.remove(token);
    }

    public boolean existsTicket(String token) {
        return TICKETS.containsKey(token);
    }

    public int getIncome() {
        return TICKETS.values()
                .stream()
                .mapToInt(Seat::getPrice)
                .sum();
    }

    public int getAvailableSeats() {
        return SEATS.size() - TICKETS.size();
    }

    public int getPurchasedTickets() {
        return TICKETS.size();
    }
}