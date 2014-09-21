package com.pb.test.lab6;

import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Olga
 */
public class RunnableOutputDataQueue implements Runnable {

    private final BlockingQueue queue;
    volatile static private int counter = 0;
    volatile static private int counter1 = 0;

    public RunnableOutputDataQueue(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public synchronized void run() {
        try {
            String str1 = "11111";
            String str2 = "22222";
            String str3 = "33333";
            System.out.println(str1 + " В контейнер помещает поток " + Thread.currentThread().getName());
            counter = ++counter;
            queue.put(str1);
            Thread.sleep(100);
            
            if (counter < 10) {
                this.wait();
            }
            this.notify();
            System.out.println(str2 + " В контейнер помещает поток " + Thread.currentThread().getName());
            queue.put(str2);
            counter1 = ++counter1;
            Thread.sleep(100);
            
            if (counter1 < 10) {
                this.wait();
            }
            
            this.notify();
            System.out.println(str3 + " В контейнер помещает поток " + Thread.currentThread().getName());
            queue.put(str3);
            Thread.sleep(100);
        } catch (InterruptedException ex) {
        }

    }

}
