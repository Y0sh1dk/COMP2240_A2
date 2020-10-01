/**
 *  FileName: Farmer.java
 *  Assessment: COMP2240 - A2
 *  Problem: 1
 *  Author: Yosiah de Koeyer
 *  Student No: c3329520
 *
 *  Description:
 *  Farmer class containing all relevant methods to represent a farmer crossing the bridge, the class implements
 *  'Runnable' so that it can be started as a thread.
 */


package P1;


public class Farmer implements Runnable { // Implements runnable so it can be ran as a separate thread
    private int farmerID;
    private int stepsTaken;
    private String farmerName;
    private String homeLocation;
    private String destination;
    private String currentLocation;
    private Bridge bridge;

    Farmer() {
        this.farmerID = 0;
        this.stepsTaken = 0;
        this.farmerName = "";
        this.homeLocation = "";
        this.destination = "";
        this.currentLocation = "";
        this.bridge = null;
    }

    Farmer(int id, String home, Bridge b) {
        this();
        this.farmerID = id;
        this.homeLocation = home;
        this.currentLocation = this.homeLocation;
        this.bridge = b;
        this.generateNameAndDest();
    }

    @Override
    public void run() {
        System.out.println(this.farmerName + ": Waiting for bridge. Going towards " + this.destination);
        while(true) {
//            Try to cross bridge
            try {
                bridge.getLock().acquire();
                while(this.stepsTaken < this.bridge.getBridgeLength()) {
                    this.stepsTaken += 5;
                    Thread.sleep(200);
                    System.out.println(this.farmerName + ": Crossing bridge Step " + this.stepsTaken);
                }
                System.out.println(this.farmerName + ": Across the bridge");
                this.bridge.getLock().release();
                this.stepsTaken = 0;
                this.currentLocation = this.destination;
                this.changeDestination();
                this.bridge.incrementCount();
                System.out.println("NEON = " + this.bridge.getCount());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.farmerName + ": Waiting for bridge. Going towards " + this.destination);
        }
    }

    private void generateNameAndDest() {
        if (this.homeLocation.equals("North")) {
            this.farmerName = "N_Farmer" + this.farmerID;
            this.destination = "South";
        } else if (this.homeLocation.equals("South")) {
            this.farmerName = "S_Farmer" + this.farmerID;
            this.destination = "North";
        }
    }

    private void changeDestination() {
        if (this.currentLocation.equals("North")) {
            this.destination = "South";
        } else if (this.currentLocation.equals("South")) {
            this.destination = "North";
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