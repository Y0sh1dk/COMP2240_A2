package P3;

public class Customer implements Runnable {

    private int arriveTime;         // time customer arrives at restaurant
    private String id;              // customer id
    private int eatTime;            // how long the customer takes to eat
    private int seatedTime;         // time customer got seated
    private int leaveTime;          // time customer left
    private boolean seated;
    private Seat seat;
    private Restaurant restaurant;


    Customer() {
        this.arriveTime = 0;
        this.id = null;
        this.eatTime = 0;
        this.seatedTime = 0;
        this.leaveTime = 0;
        this.seated = false;
    }

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
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (this.arriveTime <= A2P3.getTime()) {
                try {
                    restaurant.tryToSeat(this);
                    while(true) {
                        try {
                            Thread.sleep(150); // Fixes everything
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if ((A2P3.getTime() - this.getSeatedTime()) == this.getEatTime()) {
                            this.setLeaveTime(A2P3.getTime());
                            //System.out.println("Leaving " + this.getId() + " at time " + this.getLeaveTime() + " in seat id: " + seat.getId());
                            seat.setTaken(false);
                            this.seat = null;
                            break;
                        }
                    }
                    break; // Kills thread once done
                } catch (SeatUnavailableException e) {
                    //e.printStackTrace();
                }
            }
        }
    }

    public String getId() {
        return id;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    public int getEatTime() {
        return eatTime;
    }

    public int getLeaveTime() {
        return leaveTime;
    }

    public int getSeatedTime() {
        return seatedTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setArriveTime(int arriveTime) {
        this.arriveTime = arriveTime;
    }

    public void setEatTime(int eatTime) {
        this.eatTime = eatTime;
    }

    public void setLeaveTime(int leaveTime) {
        this.leaveTime = leaveTime;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setSeatedTime(int seatedTime) {
        this.seatedTime = seatedTime;
    }

    public boolean isSeated() {
        return seated;
    }

    public void setSeated(boolean seated) {
        this.seated = seated;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
