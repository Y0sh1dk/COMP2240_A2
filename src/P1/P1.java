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

import java.util.ArrayList;

public class P1 {
    public static void main(String[] args) { // Example: 'N=2, S=2'
        if (args.length != 2) { // If wrong args amount given
            System.out.println("Usage: P1 2 2");
            return;
        }
        int northFarmers;
        int southFarmers;
        try {
            northFarmers = Integer.parseInt(args[0]);
            southFarmers = Integer.parseInt(args[1]);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Usage: P1 2 2");
            return;
        }
        P1 main = new P1();
        main.run(northFarmers, southFarmers);
    }

    private void run(int northFarmers,int southFarmers) {
        Bridge bridge = new Bridge();
        ArrayList<Farmer> farmerList = generateFarmers(northFarmers, southFarmers, bridge);
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

}