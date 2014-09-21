package com.pb.test.lab6;

import java.util.concurrent.BlockingQueue;

class RunnableOutput implements Runnable {

    private final BlockingQueue queue;

    public RunnableOutput(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        while (true) {
            try {
                System.out.println(queue.take() + " достаем из контейнера");
            } catch (InterruptedException ex) {
            }
        }
    }

}
