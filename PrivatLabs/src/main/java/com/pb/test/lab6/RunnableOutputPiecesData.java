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

    @Override
    public synchronized void run() {
        try {

            System.out.println("11111");
            Thread.sleep(100);
            if (counter != 9) {
                counter = ++counter;
                this.wait();

            } else {
                counter = 0;
                this.notifyAll();
            }
            System.out.println("22222");
            Thread.sleep(100);
            if (counter != 9) {
                counter = ++counter;
                this.wait();

            } else {
                counter = 0;
                this.notifyAll();
            }
            System.out.println("33333");
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(RunnableOutputPiecesData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
