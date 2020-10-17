/**
 *  FileName: P1.java
 *  Assessment: COMP2240 - A2
 *  Problem: 1
 *  Author: Yosiah de Koeyer
 *  Student No: c3329520
 *
 *  Description:
 *  Main class file for Problem 1, accepts a file containing the input parameters via command line argument. Initializes
 *  farmers and farmer threads and then runs the simulation, outputting relevant info to the console.
 */

package P1;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class P1 {

    /**
     * Entry point for P1 class
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
            P1 main = new P1();
            main.run(filePath);
        } else if (Files.exists(Paths.get((filePath + ".txt")))) {  // If it doesnt, try adding '.txt' extension
            filePath = Paths.get((filePath + ".txt"));
            P1 main = new P1();
            main.run(filePath);
        } else {                                                    // else no file can be found, exit
            System.out.println("File " + filePath.getFileName() + " is not found");
            System.out.println("Exiting...");
        }
    }

    /**
     * run method that is called from main method if filepath provided is valid.
     * Parses the input file and gets the parameters, then generates the farmers and threads then starts the threads.
     * @param p
     */
    private void run(Path p) {
        int[] parameters = getParametersFromFile(p);
        Bridge bridge = new Bridge();
        ArrayList<Farmer> farmerList = generateFarmers(parameters[0], parameters[1], bridge);   // Generate farmers
        ArrayList<Thread> farmerThreads = generateFarmerThreads(farmerList);                    // Make farmer threads
        startThreads(farmerThreads);                                                            // Start the threads
    }

    /**
     * A method that generated the north and south farmers from the provided arguments
     * @param n the number of north farmers.
     * @param s the number of south farmers.
     * @param b the bridge object associated with the farmers
     * @return ArrayList<Farmer> containing all farmers generated
     */
    private ArrayList<Farmer> generateFarmers(int n, int s, Bridge b) {
        ArrayList<Farmer> farmerList = new ArrayList<>();
        //        Generate north farmers
        for (int i=0; i < n; i++) {
            farmerList.add(new Farmer(i+1, "North", b));
        }
        //        Generate south farmers
        for (int i=0; i < s; i++) {
            farmerList.add(new Farmer(i+1, "South", b));
        }
        return farmerList;
    }

    /**
     * generateFarmerThreads method that generates threads associated with farmer objects
     * @param farmers An ArrayList containing farmer objects
     * @return ArrayList<Thread> An ArrayList containing threads associated with farmer objects
     */
    private ArrayList<Thread> generateFarmerThreads(ArrayList<Farmer> farmers) {
        ArrayList<Thread> threads = new ArrayList<>();

        for ( Farmer f : farmers) {
            threads.add(new Thread(f));
        }
        return threads;
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
     * getParametersFromFile method that parses the file provided and extracts the parameters
     * @param p A Path object containing the path to file
     * @return int[] of size 2 containing number of North and South farmers
     */
    private int[] getParametersFromFile(Path p) {
        Scanner inputStream;
        int[] parameters = new int[2];
        try {
            inputStream = new Scanner (new File(String.valueOf(p.toAbsolutePath()))); // TODO: test!
        } catch (Exception e) {
            System.out.println(e);
            return null; // Already checks if file is valid previously so this should never happen
        }
        while (inputStream.hasNextLine()) {
            String line = inputStream.nextLine();
            if (line.contains("N=") && line.contains("S=")) {
                String[] splitLine = line.split(" ", 2);
                parameters[0] = Integer.parseInt(String.valueOf(splitLine[0].charAt(2)));
                parameters[1] = Integer.parseInt(String.valueOf(splitLine[1].charAt(2)));
            }
        }
        return parameters;
    }

}