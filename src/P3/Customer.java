package P3;

public class Customer implements Runnable {

    private int arriveTime;     // time customer arrives at restaurant
    private String id;             // customer id
    private int eatTime;        // how long the customer takes to eat

    private int seatedTime;     // time customer got seated
    private int leaveTime;      // time customer left


    private Restaurant restaurant;

    Customer() {
        this.arriveTime = 0;
        this.id = null;
        this.eatTime = 0;
        this.seatedTime = 0;
        this.leaveTime = 0;
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
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (this.arriveTime <= A2P3.getTime()) {
                try {
                    restaurant.tryToSeat();
                    break; // ?
                } catch (SeatUnavailableException e) {
                    e.printStackTrace();
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

}
