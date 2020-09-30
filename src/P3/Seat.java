package P3;

public class Seat {
    private int id;
    private Restaurant restaurant;
    private boolean isTaken;

    Seat(int id, Restaurant r) {
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
        c.setSeatedTime(A2P3.getTime());
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
