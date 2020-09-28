package P2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Restaurant {
    private static final int CLEANING_TIME = 5;
    private static final int MAX_CUSTOMERS = 5;
    private Semaphore lock = new Semaphore(MAX_CUSTOMERS, true);

    private Queue<Customer> customerQueue;


//    Restaurant() {
//        customerQueue = new LinkedList<Customer>();
//    }
//
//    Restaurant(LinkedList<Customer> customers) {
//        this.customerQueue = customers; // It is assumed that customers in file are already in order :)
//    }




    public static int getCleaningTime() {
        return CLEANING_TIME;
    }

    public static int getMaxCustomers() {
        return MAX_CUSTOMERS;
    }

    public Semaphore getLock() {
        return lock;
    }

    public int getAvailableSeats() { // Kind of redundant since can be done with getLock()
        return this.lock.availablePermits();
    }

}
