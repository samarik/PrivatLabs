package com.pb.test.lab6;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 *
 * @author Olga
 */
public class MyMultithread {

    /**
     * @param args the command line arguments
     */
    private static BlockingQueue<String> queue = new PriorityBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException {
//        Thread t[] = new Thread[5];
//        for (int i = 0; i < t.length; i++) {
//            t[i] = new Thread(new RunnableCounter(), "Thread " + i);
//            t[i].setPriority(i + 2);
//        }
//        for (int i = 0; i < t.length; i++) {
//            t[i].start();
//            System.out.println(t[i].getName() + " started");
//        }
//        Thread.sleep(3000);
//        for (int i = 0; i < t.length; i++) {
//            t[i].interrupt();
//        }
        //задание 2,3
        System.out.println("Задание 2,3 !!!");
        Thread threadOuter = new Thread(new RunnableOutput(queue));
        threadOuter.start();
        Thread t1[] = new Thread[10];
        //Runnable runnable = new RunnableOutputPiecesData();
        Runnable runnable = new RunnableOutputDataQueue(queue);
        for (int i = 0; i < t1.length; i++) {
            t1[i] = new Thread(runnable, "Thread " + i);
        }
        for (int i = 0; i < t1.length; i++) {
            System.out.println(t1[i].getName() + " started");
            t1[i].start();
        }

    }

}
