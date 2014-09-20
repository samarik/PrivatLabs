/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pb.test.lab6;

/**
 *
 * @author Olga
 */
public class RunnableCounter implements Runnable {

    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            counter++;  
        }
    }

}

