package P2;

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
        while (true) {
            //  Small sleep fixes some Issues
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (this.arriveTime <= A2P2.getTime()) {
                if (this.restaurant.getAvailableSeats() > 0 ) { // If there are available seats
                    try {
                        Thread.sleep(10);
                        this.restaurant.getLock().acquire();
                        this.seatedTime = A2P2.getTime();
                        System.out.println(this.id + "Acquired lock");
                        while(true) {
                            Thread.sleep(10); // Fixes everything
                            if ((A2P2.getTime() - this.seatedTime) == this.eatTime) {
                                this.restaurant.getLock().release();
                                System.out.println(this.id + "          Releasing lock");
                                break;
                            }
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else { // The restaurant is full
                    while (this.restaurant.getAvailableSeats() != 0) {
                    //    Wait till restaurant empty
                    //    Do cleaning
                    }
                }
            }
        }
    }

}
