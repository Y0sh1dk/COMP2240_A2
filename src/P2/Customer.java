/**
 *  FileName: Customer.java
 *  Assessment: COMP2240 - A2
 *  Problem: 2
 *  Author: Yosiah de Koeyer
 *  Student No: c3329520
 *
 *  Description:
 *  Bridge class containing all relevant methods to represent a customer entering the restaurant.
 *  The class implements Runnable so that it can be started and ran as a thread
 */

package P2;


public class Customer implements Runnable {
    private int arriveTime;     // time customer arrives at restaurant
    private String id;             // customer id
    private int eatTime;        // how long the customer takes to eat
    private int seatedTime;     // time customer got seated
    private int leaveTime;      // time customer left
    private Restaurant restaurant;


    /**
     * Customer constructor when no args are given, initializes values
     */
    Customer() {
        this.arriveTime = 0;
        this.id = null;
        this.eatTime = 0;
        this.seatedTime = 0;
        this.leaveTime = 0;
    }

    /**
     * Customer constructor when args are given, initializes values then sets correct ones.
     * @param aTime     An int containing the arrival time of the customer
     * @param id        A String containing the ID of the customer
     * @param eTime     An int containing how long the customer spends in the restaurant
     * @param r         A Restaurant object associated with the customer
     */
    Customer(int aTime, String id, int eTime, Restaurant r) {
        this();
        this.arriveTime = aTime;
        this.id = id;
        this.eatTime = eTime;
        this.restaurant = r;
    }

    @Override
    public void run() {
        boolean finished = false;
        while (true) {
            //  Small sleep fixes some issues
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
            if (this.arriveTime <= P2.getTime() ) {
                if (this.restaurant.getAvailableSeats() > 0 && this.restaurant.isOpen()) { // If there are available seats
                    try {
                        Thread.sleep(150); //  Small sleep fixes some issues
                        this.restaurant.getLock().acquire();
                        if (this.restaurant.getAvailableSeats() == 0) {
                            this.restaurant.setOpen(false);
                        }
                    this.seatedTime = P2.getTime();
                        //System.out.println(this.id + ": Acquired lock");
                        while(true) {
                            Thread.sleep(150); // Fixes everything
                            if ((P2.getTime() - this.seatedTime) == this.eatTime) {
                                this.restaurant.getLock().release();
                                this.leaveTime = P2.getTime();
                                //System.out.println(this.id + ": Releasing lock");
                                finished = true;
                                break;
                            }
                        }
                    } catch (InterruptedException e) {
                        //e.printStackTrace();
                    }
                } else { // The restaurant is full
                    if (!this.restaurant.isReadyToClean()) {
                        while (true) {
                            this.restaurant.setReadyToClean(true);
                            if (this.restaurant.getAvailableSeats() == this.restaurant.getMaxCustomers()) { // Restaurant is empty again
                                this.restaurant.performCleaning(); // Do cleaning
                                this.restaurant.setReadyToClean(false);
                                break;
                            }
                        }
                    }
                }
            }
            if(finished) {
                this.leaveTime = P2.getTime();
                break;
            }
        }
    }

    /**
     * getId method
     * @return String containing the Id of the customer
     */
    public String getId() {
        return id;
    }

    /**
     * getArriveTime method
     * @return int containing the arrival time of the customer
     */
    public int getArriveTime() {
        return arriveTime;
    }

    /**
     * getEatTime method
     * @return int containing how long the customer stays in the restaurant
     */
    public int getEatTime() {
        return eatTime;
    }

    /**
     * getLeaveTime method
     * @return int containing the time the customer left the restaurant
     */
    public int getLeaveTime() {
        return leaveTime;
    }

    /**
     * getSeatedTime method
     * @return int containing the time the customer was seated in the restaurant
     */
    public int getSeatedTime() {
        return seatedTime;
    }

    /**
     * getRestaurant method
     * @return Restaurant the restaurant the customer is associated with
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * setId method/
     * @param id String containing the id to assign the the customer
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * setArriveTime method
     * @param arriveTime int containing the arrival time to assign to the customer
     */
    public void setArriveTime(int arriveTime) {
        this.arriveTime = arriveTime;
    }

    /**
     * setEatTime method
     * @param eatTime int containing the eat time (how long the customer spends in the restaurant) to assign to the
     *                customer
     */
    public void setEatTime(int eatTime) {
        this.eatTime = eatTime;
    }

    /**
     * setLeaveTime method
     * @param leaveTime int containing the time that the customer leaves to assign to the customer
     */
    public void setLeaveTime(int leaveTime) {
        this.leaveTime = leaveTime;
    }

    /**
     * setRestaurant method
     * @param restaurant A Restaurant object to assign to the customer
     */
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    /**
     * setSeatedTime
     * @param seatedTime int containing the time the customer was seated to assign to the customer
     */
    public void setSeatedTime(int seatedTime) {
        this.seatedTime = seatedTime;
    }

}
