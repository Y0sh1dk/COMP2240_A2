package P3;

import java.util.ArrayList;

class SeatUnavailableException extends Exception { // default visibility scope therefore only visible within package 'P3'
    SeatUnavailableException() {
        super();
    }
}

class CleaningInProgressException extends Exception { // default visibility scope therefore only visible within package 'P3'
    CleaningInProgressException() {
        super();
    }
}

public class Restaurant {
    private final int CLEANING_TIME = 5;
    private final int MAX_CUSTOMERS = 5;
    private boolean readyToClean = false;
    private boolean isOpen = true;
    private ArrayList<Seat> seats = new ArrayList<>();


    Restaurant() {
        // Initialize seats
        for (int i = 1; i <= MAX_CUSTOMERS; i++) {
            this.seats.add(new Seat(i, this));
        }
    }

    // checks if all seats are taken
    public boolean isFull() {
        for (Seat s : this.seats) {
            if (!s.isTaken()) {
                return false;
            }
        }
        return true;
    }

    // checks if no seats are taken
    public boolean isEmpty() {
        for (Seat s : this.seats) {
            if (s.isTaken()) {
                return false;
            }
        }
        return true;
    }

    public synchronized void tryToSeat(Customer c) throws SeatUnavailableException {
        if (this.isOpen && !this.readyToClean) {
            if (!this.isFull()) {
                for (Seat s : this.seats) {
                    try {
                        s.acquireSeat(c);
                        break;
                    } catch (SeatUnavailableException e) {
                        //e.printStackTrace();
                    }
                }
            } else {
                this.readyToClean = true;
                throw new SeatUnavailableException();
            }
        } else {
            if (this.isEmpty() && this.readyToClean) {
                try {
                    this.performCleaning();
                } catch (CleaningInProgressException e) {
                    //e.printStackTrace();
                }
            }
            throw new SeatUnavailableException();
        }
    }

    public boolean isReadyToClean() {
        return readyToClean;
    }

    public void setReadyToClean(boolean readyToClean) {
        this.readyToClean = readyToClean;
    }

    public int getCleaningTime() {
        return CLEANING_TIME;
    }

    public int getMaxCustomers() {
        return MAX_CUSTOMERS;
    }

    public synchronized void performCleaning() throws CleaningInProgressException {
        if (!isOpen) {
            throw new CleaningInProgressException();
        }
        //System.out.println("---PERFORMING CLEANING---");
        this.isOpen = false;
        this.readyToClean = false;
        int cleaningStart = A2P3.getTime();
        while (A2P3.getTime() - cleaningStart < this.getCleaningTime()) {
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
        //System.out.println("---CLEANING DONE---");
        this.isOpen = true;

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        this.isOpen = open;
    }

}