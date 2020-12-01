# COMP2240 - A2
Notes:

File extensions are not necessary when running from terminal. However, it is assumed that each file is formatted
correctly and containes valid data.

## Problem 1: Sharing the bridge
Notes:

 - Main loop does not have an exit condition, will run until interrupted
 - Counter for NEON sign is stored as int like `private int count` so may overflow - NOT TESTED
 - 200ms delay between every 5 steps the farmers take

Compile:

    javac P1.java

Execute:

    java P1 [file]

## Problem 2: Covid-safe restaurant
Notes:

 - Problem 2 runs as a 'Real-time simulation', therefore will run until last customer leaves the restaurant before
 displaying any results
 - The user is updated at each time increment so to not assume the program is frozen
 
Compile:

    javac P2.java

Execute:

    java P2 [file]

## Problem 3: Covid-safe restaurant using monitor
Notes:
 - Problem 3 runs as a 'Real-time simulation', therefore will run until last customer leaves the restaurant before
 displaying any results
  - The user is updated at each time increment so to not assume the program is frozen

Compile:

    javac P3.java

Execute:

    java P3 [file]