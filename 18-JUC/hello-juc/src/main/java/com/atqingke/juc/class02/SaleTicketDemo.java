package com.atqingke.juc.class02;

import java.util.concurrent.locks.ReentrantLock;

class Ticket {
    private int number = 50;
    ReentrantLock lock = new ReentrantLock(true);

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "sale the: \t" + (number--) + "\t and have: \t" + number + "\tnow");
            }
        } finally {
            lock.unlock();
        }
    }
}

public class SaleTicketDemo {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(()->{
            for (int i = 0; i < 55; i++) ticket.sale();
        }, "aaa").start();
        new Thread(()->{
            for (int i = 0; i < 55; i++) ticket.sale();
        }, "bbb").start();
        new Thread(()->{
            for (int i = 0; i < 55; i++) ticket.sale();
        }, "ccc").start();
    }
}

