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

    Customer(int aTime, String id, int eTime) {
        this();
        this.arriveTime = aTime;
        this.id = id;
        this.eatTime = eTime;
    }


    @Override
    public void run() {

    }


}