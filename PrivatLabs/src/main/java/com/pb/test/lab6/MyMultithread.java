package com.pb.test.lab6;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Olga
 */
public class MyMultithread {

    /**
     * @param args the command line arguments
     */
    private static BlockingQueue queue = new LinkedBlockingQueue<String>();

    //private static BlockingQueue<String> queue;

    public static void main(String[] args) throws InterruptedException {
        Thread t[] = new Thread[5];
        RunnableCounter r[] = new RunnableCounter[5];
        for (int i = 0; i < t.length; i++) {
            r[i] = new RunnableCounter();
            t[i] = new Thread(r[i], "Thread " + i);
            t[i].setPriority(i + 1);
        }
        for (int i = 0; i < t.length; i++) {
            t[i].start();
            System.out.println(t[i].getName() + " started");
        }
        Thread.sleep(3000);
        for (int i = 0; i < t.length; i++) {
            System.out.println(t[i].getName() + " stop counter " + r[i].getCounter());
            t[i].interrupt();

        }
        //задание 2,3
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
        System.out.println("будем выводить !!!");
        Thread threadOuter=new Thread(new RunnableOutput(queue));
         threadOuter.start();
         System.out.println("конец main !!! " );
        

    }

}
