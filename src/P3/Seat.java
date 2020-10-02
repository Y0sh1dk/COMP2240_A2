/**
 *  FileName: Seat.java
 *  Assessment: COMP2240 - A2
 *  Problem: 3
 *  Author: Yosiah de Koeyer
 *  Student No: c3329520
 *
 *  Description:
 *  Seat class containing all relevant methods to represent a seat at the restaurant
 */

package P3;

public class Seat {
    private int id;                 // A seat identifier
    private Restaurant restaurant;  // The restaurant associated with this seat
    private boolean isTaken;        // Taken status of the seat

    /**
     * Seat constructor when no args are given, initializes values
     */
    Seat() {
        this.id = 0;
        this.restaurant = null;
        this.isTaken = false;
    }

    /**
     * Seat constructor when args are given, initializes values then sets correct ones
     * @param id An int containing the ID of the customer
     * @param r A Restaurant object that the seats belong too
     */
    Seat(int id, Restaurant r) {
        this();
        this.id = id;
        this.restaurant = r;
        this.isTaken = false;
    }

    /**
     * aqurieSeat method, does not particularly need to be synchronized as the method the calls it is synchronized, but
     * best to leave it synchronized incase it is used elsewhere. Allows only one Customer to try and
     * acquire it at a time. If it is successfully acquired, sets the seated time of the customer and its seated status
     * @param c The customer attempting to be seated there
     * @throws SeatUnavailableException if the seat is already taken, throws a exception
     */
    public synchronized void acquireSeat(Customer c) throws SeatUnavailableException {
        if (this.isTaken) {
            throw new SeatUnavailableException();
        }
        this.isTaken = true;
        c.setSeat(this);
        c.setSeatedTime(P3.getTime());
        c.setSeated(true);
        //System.out.println("Seated:" + c.getId() + " at time " + c.getSeatedTime() + " in seat id: " + this.id);
    }

    /**
     * getRestaurant method
     * @return Restaurant object associated with ths seat
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * getId method
     * @return int containing the Id of the seat
     */
    public int getId() {
        return id;
    }

    /**
     * isTaken method
     * @return boolean if the seat is taken, returns true, else returns false
     */
    public boolean isTaken() {
        return isTaken;
    }

    /**
     * setRestaurant method
     * @param restaurant A Restaurant object to assign to the seat
     */
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    /**
     * setId method/
     * @param id String containing the id to assign the the seat
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * setTaken method
     * @param taken boolean to set the taken status of the seat
     */
    public void setTaken(boolean taken) {
        isTaken = taken;
    }

}
