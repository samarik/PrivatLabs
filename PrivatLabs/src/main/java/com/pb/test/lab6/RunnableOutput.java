package com.pb.test.lab6;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

class RunnableOutput implements Runnable {

    private final BlockingQueue queue;

    public RunnableOutput(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
while (true){
    try {
        System.out.println(queue.take()+" достаем из контейнера");
    } catch (InterruptedException ex) {
        Logger.getLogger(RunnableOutput.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    }

}
