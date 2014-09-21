/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.test.lab6;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Olga
 */
public class RunnableCounter implements Runnable {

    private int counter = 0;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            counter++;  
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                break;
            }
        }
        System.out.println(Thread.currentThread().getName() + " stoped. Counter is - " + counter);
    }

}

