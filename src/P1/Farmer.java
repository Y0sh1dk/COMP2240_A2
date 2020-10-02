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

    /**
     * Farmer constructor when no args are given, initializes values
     */
    Farmer() {
        this.farmerID = 0;
        this.stepsTaken = 0;
        this.farmerName = "";
        this.homeLocation = "";
        this.destination = "";
        this.currentLocation = "";
        this.bridge = null;
    }

    /**
     * Farmer constructor when 3 args are given, initializes values, then sets correct ones.
     * @param id        An int containing the id of the farmer
     * @param home      A String containing the home location of the farmer
     * @param b         A Bridge object associated with the farmer
     */
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

    /**
     * generateNameAndDest method, generates the farmer name and its destination
     * so that it doesnt need to be provided in the constructor
     */
    private void generateNameAndDest() {
        if (this.homeLocation.equals("North")) {
            this.farmerName = "N_Farmer" + this.farmerID;
            this.destination = "South";
        } else if (this.homeLocation.equals("South")) {
            this.farmerName = "S_Farmer" + this.farmerID;
            this.destination = "North";
        }
    }

    /**
     * changeDestination method, changes the current destination to the correct one as
     * determined by the current location.
     */
    private void changeDestination() {
        if (this.currentLocation.equals("North")) {
            this.destination = "South";
        } else if (this.currentLocation.equals("South")) {
            this.destination = "North";
        }
    }

    /**
     * getBridge method
     * @return Bridge the bridge the farmer is associated with
     */
    public Bridge getBridge() {
        return bridge;
    }

    /**
     * getFarmerID method
     * @return int containing the ID of the farmer
     */
    public int getFarmerID() {
        return farmerID;
    }

    /**
     * getDestination method
     * @return String containing the current destination of the farmer
     */
    public String getDestination() {
        return destination;
    }

    /**
     * getFarmerName method
     * @return String containing the farmers name
     */
    public String getFarmerName() {
        return farmerName;
    }

    /**
     * getHomeLocation method
     * @return String containing the home location of the farmer
     */
    public String getHomeLocation() {
        return homeLocation;
    }

    /**
     * getBridge method
     * @param bridge object to set as the farmers bridge
     */
    public void setBridge(Bridge bridge) {
        this.bridge = bridge;
    }

    /**
     * setDestination method
     * @param destination A String to set as farmers destination
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * setFarmerID method
     * @param farmerID An int to set as farmers ID
     */
    public void setFarmerID(int farmerID) {
        this.farmerID = farmerID;
    }

    /**
     * setFarmerName method
     * @param farmerName A String to set as the farmers name
     */
    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    /**
     * setHomeLocation method
     * @param homeLocation A String to set as the farmers home location.
     */
    public void setHomeLocation(String homeLocation) {
        this.homeLocation = homeLocation;
    }

}