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

    public synchronized void acquire() {
        this.isTaken = true;

        // do stuff

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
