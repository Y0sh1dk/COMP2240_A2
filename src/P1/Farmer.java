package P1;

import java.util.concurrent.*;


public class Farmer implements Runnable { // Implements runnable so it can be ran as a separate thread
    private int farmerID;
    private String farmerName;
    private String homeLocation;
    private String destination;
    private Bridge bridge;



    @Override
    public void run() {

    }

    Farmer() {
        this.farmerID = 0;
        this.farmerName = "";
        this.homeLocation = "";
        this.destination = "";
        this.bridge = null;

    }

    Farmer(int id, String home, Bridge b) {
        this();
        this.farmerID = id;
        this. homeLocation = home;
        this.bridge = b;
        this.generateName();
    }

    private void generateName() {
        if (this.destination.equals("North")) {
            this.farmerName = "N_Farmer" + this.farmerID;
        } else if (this.destination.equals("South")) {
            this.farmerName = "S_Farmer" + this.farmerID;
        } else {
//            TODO: exeption?
            System.out.println("Invalid Home");
        }
    }

}
