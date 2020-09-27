package P1;

import java.util.concurrent.*;

public class Bridge {
    private static final int BRIDGE_LENGTH = 20; // 20 steps to cross the bridge

    // Only one thread can access and waiting thread are granted a permit in a queue with 'true'
    private Semaphore lock = new Semaphore(1, true);
    private int count; // Number of farmers that have crossed the bridge

    public static int getBridgeLength() {
        return BRIDGE_LENGTH;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Semaphore getLock() {
        return lock;
    }

    public void incrementCount() {
        this.count++;
    }

}