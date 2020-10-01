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
    private int id;
    private Restaurant restaurant;
    private boolean isTaken;

    Seat() {
        this.id = 0;
        this.restaurant = null;
        this.isTaken = false;
    }

    Seat(int id, Restaurant r) {
        this();
        this.id = id;
        this.restaurant = r;
        this.isTaken = false;
    }


    // Doesnt need to be synchronized because block that calls it is synchronized, better to be safe
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public int getId() {
        return id;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

}
