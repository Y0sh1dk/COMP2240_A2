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

    private void run(Path p) {
        int[] parameters = getParametersFromFile(p);
        Bridge bridge = new Bridge();
        ArrayList<Farmer> farmerList = generateFarmers(parameters[0], parameters[1], bridge);
        ArrayList<Thread> farmerThreads = generateFarmerThreads(farmerList);
        startThreads(farmerThreads);
    }

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

    private ArrayList<Thread> generateFarmerThreads(ArrayList<Farmer> farmers) {
        ArrayList<Thread> threads = new ArrayList<>();

        for ( Farmer f : farmers) {
            threads.add(new Thread(f));
        }
        return threads;
    }

    private void startThreads(ArrayList<Thread> threads) {
        for (Thread t : threads) {
            t.start();
        }
    }

    private int[] getParametersFromFile(Path p) {
        Scanner inputStream;
        int[] parameters = new int[2];
        try {
            inputStream = new Scanner (new File(String.valueOf(p.getFileName())));
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