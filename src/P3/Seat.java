package P3;


class SeatTakenException extends Exception { // default visibility scope therefore only visible within package 'P3'
    SeatTakenException() {
        super();
    }
}

public class Seat {
    private int id;
    private Restaurant restaurant;
    private boolean isTaken;
    //TODO: Seat has customer as a member?

    Seat(int id, Restaurant r) {
        this.id = id;
        this.restaurant = r;
        this.isTaken = false;
    }


    public void acquireSeat() throws SeatTakenException {
        if (this.isTaken) {
            throw new SeatTakenException();
        }
        synchronized (this) {
            this.isTaken = true;
            // do stuff
        }
        this.isTaken = false;
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
