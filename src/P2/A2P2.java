package P2;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class A2P2 {

//    This avoids having a separate class for time
    private static int time;

    public static int getTime() {
        return time;
    }

    public static void incrementTime() {
        A2P2.time += 1;
    }

    public static void incrementTimeBy(int t) {
        A2P2.time += t;
    }


    public static void main(String[] args) {
        if (args.length != 1) { // If no args given, exit
            System.out.println("Usage: A1 [file]");
            return;
        }
        Path filePath = Paths.get(args[0]);
        System.out.println("Using file: " + filePath);
        if (!Files.exists(filePath)) { // exit if file not valid
            System.out.println("File " + filePath.getFileName() + " is not found");
            System.out.println("Exiting...");
            return;
        }
//        If given file exits, run method "run"
        A2P2 main = new A2P2();
        main.run(filePath);
    }

    private void run(Path p) {
        Restaurant restaurant = new Restaurant();
        ArrayList<Customer> customers = readCustomersFromFile(p, restaurant);
        ArrayList<Thread> customerThreads = generateCustomerThreads(customers);
        startThreads(customerThreads);

        //THIS SLEEP IS NEEDED, DONT REMOVE!
        try {
            Thread.sleep(1000); // DONT REMOVE
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

    private void printSummary(ArrayList<Customer> customers) {
        System.out.println("\nSummary");
        System.out.println(String.format("%-9s %-9s %-9s %-9s", "Customer", "Arrives", "Seats", "Leaves"));
        for(Customer c : customers) {
            System.out.println(String.format("%-9s %-9s %-9s %-9s"
                    , c.getId(), c.getArriveTime(), c.getSeatedTime(), c.getLeaveTime()));
        }
    }


    private boolean lastJobFinished(ArrayList<Customer> customers) {
        for (Customer c : customers) {
            if (c.getLeaveTime() == 0) { // presumes customer stays in shop for atleast 1 unit of time
                return false;
            }
        }
        return true; // all jobs finished
    }

    private void startThreads(ArrayList<Thread> threads) {
        for (Thread t : threads) {
            t.start();
        }
    }

    private ArrayList<Thread> generateCustomerThreads(ArrayList<Customer> customers) {
        ArrayList<Thread> threads = new ArrayList<>();

        for (Customer c : customers) {
            threads.add(new Thread(c));
        }
        return threads;
    }

    private ArrayList<Customer> readCustomersFromFile(Path p, Restaurant r) {
        Scanner inputStream;
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            inputStream = new Scanner (new File(String.valueOf(p.getFileName())));
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
