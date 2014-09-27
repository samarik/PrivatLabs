package com.pb.test.lab6;

import java.util.concurrent.BlockingQueue;

class RunnableOutput implements Runnable {

    private final BlockingQueue queue;

    public RunnableOutput(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        String str;
        while (true) {
            try {
                str=(String)(queue.take());
                if (str.equals("end")) break;
                System.out.println(str + " достаем из контейнера");
            } catch (InterruptedException ex) {
            }
        }
    }

}
