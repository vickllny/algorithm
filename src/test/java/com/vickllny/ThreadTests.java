package com.vickllny;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTests {

    @Test
    public void test1() throws InterruptedException {
        Object resource = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (resource){
                try {
                    System.out.println("线程1获得锁");
                    resource.wait();
                    System.out.println("线程1被唤醒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        final Thread t2 = new Thread(() -> {
            synchronized (resource){
                try {
                    System.out.println("线程2获得锁");
                    TimeUnit.SECONDS.sleep(3);
                    resource.notifyAll();
                    System.out.println("调用资源的通知操作完成");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        System.out.println("主线程退出");
    }

    @Test
    public void test2() throws InterruptedException {
        //三个线程交替打印[1-99]数字
        final Counter counter = new Counter();

        final Object r1 = new Object();

        final Thread t1 = new Thread(() -> {
            while (counter.getValue() < 100) {
                synchronized (r1) {
                    try {
                        if(counter.getValue() % 3 == 0){
                            System.out.println(Thread.currentThread().getName() + " print: " + counter.incr());
                            r1.notifyAll();
                        }else {
                            r1.wait();
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });


        final Thread t2 = new Thread(() -> {
            while (counter.getValue() < 100) {
                synchronized (r1) {
                    try {
                        if(counter.getValue() % 3 == 1){
                            System.out.println(Thread.currentThread().getName() + " print: " + counter.incr());
                            r1.notifyAll();
                        }else {
                            r1.wait();
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        final Thread t3 = new Thread(() -> {
            while (counter.getValue() < 100) {
                synchronized (r1) {
                    try {
                        if(counter.getValue() % 3 == 2){
                            System.out.println(Thread.currentThread().getName() + " print: " + counter.incr());
                            r1.notifyAll();
                        }else {
                            r1.wait();
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t3.join();
        t1.join();
        t2.join();
        //通知其他线程永久退出
        synchronized (r1){
            r1.notifyAll();
        }
    }

    private volatile int count = 0;
    private volatile int turn = 0;
    @Test
    public void test3() throws InterruptedException {
        //三个线程交替打印[1-99]数字 volatile 版本
        final Counter counter = new Counter();

        final Thread t1 = new Thread(() -> {
            while (count < 100) {
                if(turn == 0){
                    System.out.println(Thread.currentThread().getName() + " print: " + count);
                    count++;
                    turn = (turn + 1) % 3;
                }else {
                    Thread.yield();
                }
            }
        });


        final Thread t2 = new Thread(() -> {
            while (count < 100) {
                if(turn == 1){
                    System.out.println(Thread.currentThread().getName() + " print: " + count);
                    count++;
                    turn = (turn + 1) % 3;
                }else {
                    Thread.yield();
                }
            }
        });

        final Thread t3 = new Thread(() -> {
            while (count < 100) {
                if(turn == 2){
                    System.out.println(Thread.currentThread().getName() + " print: " + count);
                    count++;
                    turn = (turn + 1) % 3;
                }else {
                    Thread.yield();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t3.join();
        t2.join();
    }


    static class Counter{
        private int value;

        public int incr(){
            return ++value;
        }

        public int getValue(){
            return value;
        }
    }

    @Test
    public void test4() throws InterruptedException {
        final MessageBox box = new MessageBox();
        final Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                box.produce("hello " + i);
            }
        });

        final Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                box.consumer();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    static class MessageBox {
        private String message;
        private boolean empty = true;

        public synchronized void produce(final String message){
            while (!empty){
                try {
                    this.wait();
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
            this.message = message;
            System.out.println(Thread.currentThread().getName() + "发送消息,message = " + message);
            this.empty = false;
            this.notifyAll();
        }

        public synchronized String consumer(){
            while (empty){
                try {
                    this.wait();
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            }
            this.empty = true;
            System.out.println(Thread.currentThread().getName() + "消费消息,message = " + message);
            this.notifyAll();
            return this.message;
        }
    }

    @Test
    public void test5() throws InterruptedException {
        final ReentrantLock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        final Counter counter = new Counter();
        final Thread t1 = new Thread(() -> {
            while (counter.getValue() < 100){
                lock.lock();
                try {
                    if(counter.getValue() % 2 == 0){
                        System.out.println(Thread.currentThread().getName() + " print: " + counter.incr());
                        condition.signalAll();
                    }else {
                        condition.await();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }finally {
                    lock.unlock();
                }
            }
        });

        final Thread t2 = new Thread(() -> {
            while (counter.getValue() < 100){
                lock.lock();
                try {
                    if(counter.getValue() % 2 == 1){
                        System.out.println(Thread.currentThread().getName() + " print: " + counter.incr());
                        condition.signalAll();
                    }else {
                        condition.await();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }finally {
                    lock.unlock();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        lock.lock();
        try {
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
