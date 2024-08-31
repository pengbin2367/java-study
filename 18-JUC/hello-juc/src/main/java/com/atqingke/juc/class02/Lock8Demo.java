package com.atqingke.juc.class02;

import java.util.concurrent.TimeUnit;

class Phone {
    public static synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("---------- send email ---------");
    }

    public  synchronized void sendSMS() {
        System.out.println("---------- send SMS ---------");
    }

    public void hello() {
        System.out.println("---------- hello ---------");
    }
}

public class Lock8Demo {

    public static void main(String[] args) {
//        System.out.println("************ lock1 ************");
//        lock1();
//        System.out.println("************ lock2 ************");
//        lock2();
//        System.out.println("************ lock3 ************");
//        lock3();
//        System.out.println("************ lock4 ************");
//        lock4();
//        System.out.println("************ lock5 ************");
//        lock5();
//        System.out.println("************ lock6 ************");
//        lock6();
//        System.out.println("************ lock7 ************");
//        lock7();
        System.out.println("************ lock8 ************");
        lock8();
    }

    // 有两个静态同步方法，一个普通同步方法，两部手机，先打印邮件还是短信
    public static void lock8() {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> phone.sendEmail(), "a").start();
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(() -> phone2.sendSMS(), "b").start();
    }

    // 有一个静态同步方法，一个普通同步方法，一部手机，先打印邮件还是短信
    public static void lock7() {
        Phone phone = new Phone();
        new Thread(() -> phone.sendEmail(), "a").start();
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(() -> phone.sendSMS(), "b").start();
    }

    // 有两个静态同步方法，两部手机，先打印邮件还是短信
    public static void lock6() {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> phone.sendEmail(), "a").start();
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(() -> phone2.sendSMS(), "b").start();
    }

    // 有两个静态同步方法，一部手机，先打印邮件还是短信
    public static void lock5() {
        Phone phone = new Phone();
        new Thread(() -> phone.sendEmail(), "a").start();
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(() -> phone.sendSMS(), "b").start();
    }

    // 有两部手机，先打印邮件还是短信
    public static void lock4() {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> phone.sendEmail(), "a").start();
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(() -> phone2.sendSMS(), "b").start();
    }

    // 添加一个普通的hello方法，先打印邮件还是hello？
    public static void lock3() {
        Phone phone = new Phone();
        new Thread(() -> phone.sendEmail(), "a").start();
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(() -> phone.hello(), "b").start();
    }

    // sendEmail 方法加入暂停3秒钟，先打印邮件还是短信？
    public static void lock2() {
        Phone phone = new Phone();
        new Thread(() -> phone.sendEmail(), "a").start();
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(() -> phone.sendSMS(), "b").start();
    }

    // 标准访问有ab两个线程，先打印邮件还是短信？
    public static void lock1() {
        Phone phone = new Phone();
        new Thread(() -> phone.sendEmail(), "a").start();
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(() -> phone.sendSMS(), "b").start();
    }
}
