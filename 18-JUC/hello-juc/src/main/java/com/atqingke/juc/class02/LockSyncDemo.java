package com.atqingke.juc.class02;

public class LockSyncDemo {

    Object lock = new Object();

//    public void lock1() {
//        synchronized (lock) {
//            System.out.println("************ lock1 ************");
//        }
//    }

    public synchronized void lock2() {
        System.out.println("**************** lock2 start");
    }

    public static synchronized void lock3() {
        System.out.println("**************** lock3 start");
    }

    public static void main(String[] args) {

    }
}
