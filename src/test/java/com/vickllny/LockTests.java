package com.vickllny;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

public class LockTests {

    @Test
    public void test(){
        final ReentrantLock lock = new ReentrantLock();
        lock.lock();
    }
}
