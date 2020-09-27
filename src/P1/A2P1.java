package P1;

import java.util.ArrayList;

public class A2P1 {

    public static void main(String[] args) { // Example: 'N=2, S=2'
        if (args.length != 2) { // If wrong args amount given
            System.out.println("Usage: A2P1 2 2");
            return;
        }
        int northFarmers;
        int southFarmers;
        try {
            northFarmers = Integer.parseInt(args[0]);
            southFarmers = Integer.parseInt(args[1]);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Usage: A2P1 2 2");
            return;
        }
        A2P1 main = new A2P1();
        main.run(northFarmers, southFarmers);
    }

    private void run(int northFarmers,int southFarmers) {
        int totalFarmers = northFarmers + southFarmers;
        Bridge bridge = new Bridge();
        ArrayList<Farmer> farmerList = generateFarmers(northFarmers, southFarmers, bridge);
        System.out.println("test");
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

}
