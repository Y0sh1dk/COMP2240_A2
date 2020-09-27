package P1;

import java.util.ArrayList;

public class A2P1 {
    public void main(String[] args) { // Example: 'N=2, S=2'
        if (args.length != 2) { // If wrong args amount given
            System.out.println("Usage: A2P1 'N=2, S=2'");
            return;
        }
        int northFarmers = Integer.parseInt(args[0]);
        int southFarmers = Integer.parseInt(args[1]);
        int totalFarmers = northFarmers + southFarmers;

        Bridge bridge = new Bridge();
        ArrayList<Farmer> farmerList = generateFarmers(northFarmers, southFarmers, bridge);



    }

    private ArrayList<Farmer> generateFarmers(int n, int s, Bridge b) {
        ArrayList<Farmer> farmerList = new ArrayList<>();

        //        For north farmers
        for (int i=0; i <= n; i++) {
            farmerList.add(new Farmer(i+1, "North", b));
        }
        //        For south farmers
        for (int i=0; i <= n; i++) {
            farmerList.add(new Farmer(i+1, "South", b));
        }
        return farmerList;
    }

}
