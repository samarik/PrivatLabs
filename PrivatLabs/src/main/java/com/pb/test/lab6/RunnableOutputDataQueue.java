/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.test.lab6;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Olga
 */
public class RunnableOutputDataQueue implements Runnable {

    private final BlockingQueue queue;
    static private int counter = 0;

    public RunnableOutputDataQueue(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public synchronized void run() {
        try {
            String str1="11111";
            String str2="22222";
            String str3="33333";
            System.out.println(str1+" В контейнер помещает поток "+Thread.currentThread().getName());
            queue.put(str1);
            Thread.sleep(100);
            if (counter != 9) {
                counter = ++counter;
                this.wait();

            } else {
                counter = 0;
                this.notifyAll();
            }
            System.out.println(str2+" В контейнер помещает поток "+Thread.currentThread().getName());
            queue.put(str2);
            Thread.sleep(100);
            if (counter != 9) {
                counter = ++counter;
                this.wait();

            } else {
                counter = 0;
                this.notifyAll();
            }
            System.out.println(str3+" В контейнер помещает поток "+Thread.currentThread().getName());
            queue.put(str3);
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(RunnableOutputPiecesData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
