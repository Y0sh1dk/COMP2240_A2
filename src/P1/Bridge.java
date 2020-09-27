package P1;

import java.util.concurrent.*;

public class Bridge {
    private static final int BRIDGE_LENGTH = 20; // 20 steps to cross the bridge

    private int count; // Number of farmers that have crossed the bridge
    private Semaphore lock = new Semaphore(1, true); // Only one thread can access and waiting thread are granted a permit in correct order with 'true'


}
