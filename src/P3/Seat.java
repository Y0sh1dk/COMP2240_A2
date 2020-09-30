package P3;



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


    public void acquireSeat(Customer c) throws SeatUnavailableException {
        if (this.isTaken) {
            throw new SeatUnavailableException();
        }
        synchronized (this) {
            this.isTaken = true;
            c.setSeatedTime(A2P3.getTime());
            System.out.println("Seated:" + c.getId() + " at time " + c.getSeatedTime() + " in seat id: " + this.id);
            while(true) {

                try {
                    Thread.sleep(150); // Fixes everything
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if ((A2P3.getTime() - c.getSeatedTime()) == c.getEatTime()) {
                    c.setLeaveTime(A2P3.getTime());
                    System.out.println("Leaving " + c.getId() + " at time " + c.getLeaveTime() + " in seat id: " + this.id);
                    this.isTaken = false;
                    break;
                }
            }
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
