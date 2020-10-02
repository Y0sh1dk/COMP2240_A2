/**
 *  FileName: Bridge.java
 *  Assessment: COMP2240 - A2
 *  Problem: 1
 *  Author: Yosiah de Koeyer
 *  Student No: c3329520
 *
 *  Description:
 *  Bridge class containing all relevant methods to represent the bridge that the farmers will attempt to cross.
 *  To limit the number of farmers crossing, the class contains a private Semaphore.
 */

package P1;

import java.util.concurrent.*;

public class Bridge {
    private final int BRIDGE_LENGTH = 20; // 20 steps to cross the bridge

    // Only one thread can access and waiting thread are granted a permit in a queue with 'true'
    private Semaphore lock = new Semaphore(1, true);
    private int count; // Number of farmers that have crossed the bridge

    /**
     * getBridgeLength method
     * @return int value of the length of the bridge
     */
    public int getBridgeLength() {
        return this.BRIDGE_LENGTH;
    }

    /**
     * getCount method
     * @return int value of the number of times farmers have crossed the bridge
     */
    public int getCount() {
        return count;
    }

    /**
     * setCount method, sets count variable to the given value
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * getLock method
     * @return Semaphore used to limit farmers crossing the bridge
     */
    public Semaphore getLock() {
        return lock;
    }

    /**
     * incrementCount method, used to increment the count variable by 1
     */
    public void incrementCount() {
        this.count++;
    }

}