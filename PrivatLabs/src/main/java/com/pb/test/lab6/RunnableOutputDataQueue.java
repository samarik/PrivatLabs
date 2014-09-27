package com.pb.test.lab6;

import java.util.concurrent.BlockingQueue;

/**
 *
 * @author Olga
 */
public class RunnableOutputDataQueue implements Runnable {

    private final BlockingQueue queue;
    volatile static private int counter = 0;
    String str1 = "11111";
    String str2 = "22222";
    String str3 = "33333";

    public RunnableOutputDataQueue(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public synchronized void run() {
        try {
            System.out.println(str1 + " В контейнер помещает поток " + Thread.currentThread().getName());
            queue.put(str1);
            Thread.sleep(100);
            System.out.println(str2 + " В контейнер помещает поток " + Thread.currentThread().getName());
            queue.put(str2);
            Thread.sleep(100);
            System.out.println(str3 + " В контейнер помещает поток " + Thread.currentThread().getName());
            queue.put(str3);
            Thread.sleep(100);
            counter = ++counter;
            if (counter > 9) {
                queue.put("end");
            }

        } catch (InterruptedException ex) {
        }

    }

}
