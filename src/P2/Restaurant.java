package P2;

import java.util.concurrent.Semaphore;

public class Restaurant {
    private final int CLEANING_TIME = 5;
    private final int MAX_CUSTOMERS = 5;
    private boolean readyToClean = false;
    private boolean isOpen = true;
    private Semaphore lock = new Semaphore(MAX_CUSTOMERS, true);
    private Semaphore cleaningLock = new Semaphore(1, true);


    public boolean isReadyToClean() {
        return this.readyToClean;
    }

    public void setReadyToClean(boolean readyToClean) {
        this.readyToClean = readyToClean;
    }

    public int getCleaningTime() {
        return this.CLEANING_TIME;
    }

    public int getMaxCustomers() {
        return this.MAX_CUSTOMERS;
    }

    public Semaphore getLock() {
        return lock;
    }

    public Semaphore getCleaningLock() {
        return cleaningLock;
    }

    public int getAvailableSeats() { // Kind of redundant since can be done with getLock()
        return this.lock.availablePermits();
    }

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

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
