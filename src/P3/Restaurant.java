/**
 *  FileName: Restaurant.java
 *  Assessment: COMP2240 - A2
 *  Problem: 3
 *  Author: Yosiah de Koeyer
 *  Student No: c3329520
 *
 *  Description:
 *  Restaurant class containing all relevant methods to represent a restaurant with seats that customers try and aqurie.
 *  The class implements Runnable so that it can be started and ran as a thread.
 *
 *  Also contains custom exception classes
 */

package P3;

import java.util.ArrayList;

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

    /**
     * isFull method
     * @return boolean true if restaurant is full, false if not full
     */
    public boolean isFull() {
        for (Seat s : this.seats) {
            if (!s.isTaken()) {
                return false;
            }
        }
        return true;
    }

    /**
     * isEmpty method
     * @return boolean true if restaurant is empty, false if not
     */
    public boolean isEmpty() {
        for (Seat s : this.seats) {
            if (s.isTaken()) {
                return false;
            }
        }
        return true;
    }

    /**
     * isReadyToClean method
     * @return boolean if restaurant is ready to clean, returns true, else false.
     */
    public boolean isReadyToClean() {
        return this.readyToClean;
    }

    /**
     * setReadyToClean method
     * @param readyToClean A boolean to set ready to clean status of the restaurant
     */
    public void setReadyToClean(boolean readyToClean) {
        this.readyToClean = readyToClean;
    }

    /**
     * getCleaningTime method
     * @return a int containing the time cleaning takes at this restaurant
     */
    public int getCleaningTime() {
        return this.CLEANING_TIME;
    }

    /**
     * getMaxCustomers method
     * @return int containing the max customers this restaurant can take
     */
    public int getMaxCustomers() {
        return this.MAX_CUSTOMERS;
    }

    /**
     * performCleaning method
     * When called, closes restaurant until cleaning is done.
     * Is synchronized so that only one cleaning can happen at a time
     * @throws CleaningInProgressException if cleaning is currently in progress
     */
    public synchronized void performCleaning() throws CleaningInProgressException {
        if (!isOpen) {
            throw new CleaningInProgressException();
        }
        //System.out.println("---PERFORMING CLEANING---");
        this.isOpen = false;
        this.readyToClean = false;
        int cleaningStart = P3.getTime();
        while (P3.getTime() - cleaningStart < this.getCleaningTime()) {
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

    /**
     * isOpen method
     * @return boolean If the restaurant is open, returns true, else returns false
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * setOpen method
     * @param open A boolean to set the open status of the restaurant
     */
    public void setOpen(boolean open) {
        this.isOpen = open;
    }

}

/**
 * Custom exception that is thrown if a seat is currently unavailable
 */
class SeatUnavailableException extends Exception { // default visibility scope therefore only visible within package 'P3'
    SeatUnavailableException() {
        super();
    }
}

/**
 * Custom exception that is thrown if a customer tries to be seated but cleaning is in progress
 */
class CleaningInProgressException extends Exception { // default visibility scope therefore only visible within package 'P3'
    CleaningInProgressException() {
        super();
    }
}
