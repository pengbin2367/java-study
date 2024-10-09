package com.atqingke.juc.class03;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class InterruptDemo {

    public static void main(String[] args) {
        interruptApi();
    }

    private static void interruptApi() {
        Thread a = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("isInterrupted updated true");
                    break;
                }
                System.out.println("a hello isInterrupted");
            }
        }, "a");
        a.start();

        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(a::interrupt, "b").start();
    }

    private static final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    private static void interruptWithAtomicBoolean() {
        new Thread(() -> {
            while (true) {
                if (atomicBoolean.get()) {
                    System.out.println("atomicBoolean updated true");
                    break;
                }
                System.out.println("a hello atomicBoolean");
            }
        }, "a").start();

        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            atomicBoolean.set(true);
        }, "b").start();
    }

    private static volatile boolean flag = false;
    private static void interruptWithVolatile() {
        new Thread(() -> {
            while (true) {
                if (flag) {
                    System.out.println("flag updated true");
                    break;
                }
                System.out.println("a hello volatile");
            }
        }, "a").start();

        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            flag = true;
        }, "b").start();
    }
}
