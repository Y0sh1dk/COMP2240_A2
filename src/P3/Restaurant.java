package P3;

import java.util.concurrent.Semaphore;

public class Restaurant {
    private static final int CLEANING_TIME = 5;
    private static final int MAX_CUSTOMERS = 5;
    private static boolean readyToClean = false;
    private boolean isOpen = true;
    private Semaphore lock = new Semaphore(MAX_CUSTOMERS, true);
    private Semaphore cleaningLock = new Semaphore(1, true);


    public static boolean isReadyToClean() {
        return readyToClean;
    }

    public static void setReadyToClean(boolean readyToClean) {
        Restaurant.readyToClean = readyToClean;
    }


    public static int getCleaningTime() {
        return CLEANING_TIME;
    }

    public static int getMaxCustomers() {
        return MAX_CUSTOMERS;
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
        int cleaningStart = A2P3.getTime();
        while (A2P3.getTime() - cleaningStart < Restaurant.getCleaningTime()) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        //System.out.println("---CLEANING DONE---");
        this.isOpen = true;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
