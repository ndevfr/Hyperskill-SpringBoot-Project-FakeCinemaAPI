package cinema.entities;

public class CinemaStatistics {

    private int currentIncome;
    private int numberOfAvailableSeats;
    private int numberOfPurchasedTickets;

    public CinemaStatistics(Builder builder) {
        this.currentIncome = builder.currentIncome;
        this.numberOfAvailableSeats = builder.numberOfAvailableSeats;
        this.numberOfPurchasedTickets = builder.numberOfPurchasedTickets;
    }


    public int getNumberOfPurchasedTickets() {
        return numberOfPurchasedTickets;
    }

    public int getNumberOfAvailableSeats() {
        return numberOfAvailableSeats;
    }

    public int getCurrentIncome() {
        return currentIncome;
    }

    public static class Builder {
        private int currentIncome;
        private int numberOfAvailableSeats;
        private int numberOfPurchasedTickets;

        public Builder currentIncome(int currentIncome) {
            this.currentIncome = currentIncome;
            return this;
        }

        public Builder numberOfAvailableSeats(int numberOfAvailableSeats) {
            this.numberOfAvailableSeats = numberOfAvailableSeats;
            return this;
        }

        public Builder numberOfPurchasedTickets(int numberOfPurchasedTickets) {
            this.numberOfPurchasedTickets = numberOfPurchasedTickets;
            return this;
        }

        public CinemaStatistics build() {
            return new CinemaStatistics(this);
        }
    }
}