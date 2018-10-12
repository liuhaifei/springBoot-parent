package com.xinho.springboot.jdk;

import java.util.concurrent.CountDownLatch;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/6/2110:38
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        int count=10;
        CountDownLatch countDownLatch=new CountDownLatch(count);

        for (int i=0;i<10;i++){
            new Thread(){
                @Override
                public void run() {
                    System.out.println("创建了线程："+Thread.currentThread().getName());
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                }
            }.start();
            countDownLatch.countDown();
        }

    }
}
