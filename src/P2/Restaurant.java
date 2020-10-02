/**
 *  FileName: Restaurant.java
 *  Assessment: COMP2240 - A2
 *  Problem: 2
 *  Author: Yosiah de Koeyer
 *  Student No: c3329520
 *
 *  Description:
 *  Restaurant class containing all relevant methods to represent a restaurant that the customers will try to be seated
 *  in. Uses semaphores to control access to the restaurant and to the cleaning process.
 */

package P2;

import java.util.concurrent.Semaphore;

public class Restaurant {
    private final int CLEANING_TIME = 5;    // How long cleaning takes
    private final int MAX_CUSTOMERS = 5;    // max number of customers allowed in store
    private boolean readyToClean = false;   // Status if restaurant is ready to clean
    private boolean isOpen = true;          // Status if restaurant is open
    // Semaphore to only allow up to the max customers access to this restaurant
    private Semaphore lock = new Semaphore(MAX_CUSTOMERS, true);
    // Semaphore to only allow cleaning to happen once at a time
    private Semaphore cleaningLock = new Semaphore(1, true);

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
     * getLock method
     * @return Semaphore used to control how many customers can be seated at the restaurant
     */
    public Semaphore getLock() {
        return lock;
    }

    /**
     * getCleaningLock method
     * @return Semaphore used to make sure cleaning can only happen once at a time
     */
    public Semaphore getCleaningLock() {
        return cleaningLock;
    }

    /**
     * getAvailableSeats method
     * @return int containing the number of free seats at the restaurant
     */
    public int getAvailableSeats() { // Kind of redundant since can be done with getLock()
        return this.lock.availablePermits();
    }

    /**
     * performCleaning method, stops any customers entering the restaurant whilst cleaning is in progress
     */
    public void performCleaning() {
        //System.out.println("---PERFORMING CLEANING---");
        int cleaningStart = P2.getTime();
        while (P2.getTime() - cleaningStart < this.getCleaningTime()) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        this.isOpen = true;
        //System.out.println("---CLEANING DONE---");
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
