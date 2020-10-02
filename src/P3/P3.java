/**
 *  FileName: P3.java
 *  Assessment: COMP2240 - A2
 *  Problem: 3
 *  Author: Yosiah de Koeyer
 *  Student No: c3329520
 *
 *  Description:
 *  Main class file for Problem 3, accepts a file containing the input parameters via command line argument. Initializes
 *  customer threads and then runs the simulation, outputting relevant info to the console.
 */

package P3;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class P3 {

    //    This avoids having a separate class for time, static so can be accessed from other classes without passing it
    private static int time = 0;

    public static int getTime() {
        return time;
    }

    public static void incrementTime() {
        P3.time += 1;
    }

    public static void incrementTimeBy(int t) {
        P3.time += t;
    }

    /**
     *  Entry point for P3 class
     * @param args used to pass an absolute or relative file path, works with and without '.txt' extension
     * @return Nothing.
     */
    public static void main(String[] args) {
        if (args.length != 1) { // If no args given, exit
            System.out.println("Usage: A1 [file]");
            return;
        }
        Path filePath = Paths.get(args[0]);
        System.out.println("Using file: " + filePath);

        if (Files.exists(filePath)) {                               // If file exists, run
            P3 main = new P3();
            main.run(filePath);
        } else if (Files.exists(Paths.get((filePath + ".txt")))) {  // If it doesnt, try adding '.txt' extension
            filePath = Paths.get((filePath + ".txt"));
            P3 main = new P3();
            main.run(filePath);
        } else {                                                    // else no file can be found, exit
            System.out.println("File " + filePath.getFileName() + " is not found");
            System.out.println("Exiting...");
        }
    }

    /**
     * run method, gets called and passed a filepath after the file input argument has been sanitized. Instantiates a
     * restaurant, reads customers from file, creates and starts threads, then runs the simulation. Once all customers
     * have passed through the restaurant, a summary is printed
     * @param p
     */
    private void run(Path p) {
        Restaurant restaurant = new Restaurant();
        ArrayList<Customer> customers = readCustomersFromFile(p, restaurant);
        ArrayList<Thread> customerThreads = generateCustomerThreads(customers);
        startThreads(customerThreads);

        //THIS SLEEP IS NEEDED, DONT REMOVE!
        try {
            Thread.sleep(1000); // likely not this long
        } catch (Exception e) {
            System.out.println(e);
        }

        while(true) {
            System.out.println("Running Simulation... (time=" + time + ")");
            //System.out.println("Permits Available: " + restaurant.getLock().availablePermits());
            incrementTime();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }
            if (lastJobFinished(customers)) {
                break;
            }
        }
        System.out.println("Simulation Done!");
        this.printSummary(customers);
    }

    /**
     * printSummary method, prints a summary of the simulation using values stored in the customer objects
     * @param customers An ArrayList of the customers
     */
    private void printSummary(ArrayList<Customer> customers) {
        System.out.println("\nSummary");
        System.out.println(String.format("%-9s %-9s %-9s %-9s", "Customer", "Arrives", "Seats", "Leaves"));
        for(Customer c : customers) {
            System.out.println(String.format("%-9s %-9s %-9s %-9s"
                    , c.getId(), c.getArriveTime(), c.getSeatedTime(), c.getLeaveTime()));
        }
    }

    /**
     * lastJobFinished, checks if all jobs have finished or not
     * @param customers An ArrayList containing the customers
     * @return boolean true if all jobs have finished, false if they have not
     */
    private boolean lastJobFinished(ArrayList<Customer> customers) {
        for (Customer c : customers) {
            if (c.getLeaveTime() == 0) { // presumes customer stays in shop for atleast 1 unit of time
                return false;
            }
        }
        return true; // all jobs finished
    }

    /**
     * startThreads method that calls the start() method for each thread
     * @param threads An ArrayList containing the threads to be started
     */
    private void startThreads(ArrayList<Thread> threads) {
        for (Thread t : threads) {
            t.start();
        }
    }

    /**
     * generateFarmerThreads method that generates threads associated with the customer objects
     * @param customers An ArrayList containing the customers
     * @return ArrayList<Thread> An ArrayList containing the threads
     */
    private ArrayList<Thread> generateCustomerThreads(ArrayList<Customer> customers) {
        ArrayList<Thread> threads = new ArrayList<>();

        for (Customer c : customers) {
            threads.add(new Thread(c));
        }
        return threads;
    }

    /**
     * readCustomersFromFile method, parses the given file to extract the customer information
     * from it and return customer objects
     * @param p A Path object containing the path to the file
     * @param r The restaurant associated with the customers
     * @return ArrayList<Customer> An ArrayList containing the customers from the file
     */
    private ArrayList<Customer> readCustomersFromFile(Path p, Restaurant r) {
        Scanner inputStream;
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            inputStream = new Scanner (new File(String.valueOf(p.toAbsolutePath()))); // TODO: test!
        } catch (Exception e) {
            System.out.println(e);
            return null; // Already checks if file is valid previously so this should never happen
        }
        while (inputStream.hasNextLine()) {
            String line = inputStream.nextLine();
            if (!line.contains("END")) {
                String[] splitLine = line.split(" ", 3);
                customers.add(new Customer(Integer.parseInt(splitLine[0]),
                        splitLine[1], Integer.parseInt(splitLine[2]), r));
            }
        }
        return customers;
    }

}
