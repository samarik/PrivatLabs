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
public class RunnableOutputPiecesData implements Runnable {

    static private int counter = 0;
    static private int counter1 = 0;

    @Override
    public synchronized void run() {
        try {

            System.out.println("11111");
            Thread.sleep(100);
            counter = ++counter;
            if (counter < 10) {
                System.out.println("1 раз ложится спать поток " + Thread.currentThread().getName());
                this.wait();

            } else {
                System.out.println("все проснулись и ждут своей очереди " + Thread.currentThread().getName());
                this.notifyAll();
            }
            System.out.println("22222 " + Thread.currentThread().getName());
            Thread.sleep(100);
            counter1 = ++counter1;
            if (counter1 < 10) {
                System.out.println("2 ложится спать поток " + Thread.currentThread().getName());
                this.wait();

            } else {
                System.out.println("2все проснулись и ждут своей очереди " + Thread.currentThread().getName());
                this.notifyAll();
            }
            System.out.println("33333 " + Thread.currentThread().getName());
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(RunnableOutputPiecesData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
