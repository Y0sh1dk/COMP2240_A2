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
//        Try to use bridge?, try to aquire lock, if not wait?
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
        this.homeLocation = home;
        this.bridge = b;
        this.generateNameAndDest();
    }

    private void generateNameAndDest() {
        if (this.homeLocation.equals("North")) {
            this.farmerName = "N_Farmer" + this.farmerID;
            this.destination = "South";
        } else if (this.homeLocation.equals("South")) {
            this.farmerName = "S_Farmer" + this.farmerID;
            this.destination = "North";
        } else {
//            TODO: exeption?
            System.out.println("Invalid Home");
        }
    }

    public Bridge getBridge() {
        return bridge;
    }

    public int getFarmerID() {
        return farmerID;
    }

    public String getDestination() {
        return destination;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public String getHomeLocation() {
        return homeLocation;
    }

    public void setBridge(Bridge bridge) {
        this.bridge = bridge;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setFarmerID(int farmerID) {
        this.farmerID = farmerID;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public void setHomeLocation(String homeLocation) {
        this.homeLocation = homeLocation;
    }

}
