package Main;

import Exception.*;
import Thread.*;
import Transport.*;
import Interface.*;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main (String[] args) throws DuplicateModelNameException {
        //Task1();
        //Task2();
        //Task3();
        //Task4();
        Task5();
    }

    public static void Task1(){
        Transport auto = new Auto("BMW", 1200);

        Thread threadNames = new ThreadNames(auto);
        Thread threadPrice = new ThreadPrice(auto);

        threadNames.setPriority(Thread.MAX_PRIORITY);
        threadPrice.setPriority(Thread.MIN_PRIORITY);

        threadNames.start();
        threadPrice.start();
    }

    public static void Task2(){
        Transport auto = new Auto("BMW", 12);

        TransportSynchronizer transportSynchronizer = new TransportSynchronizer(auto);

        Runnable runnableNames = new RunnableNames(transportSynchronizer);
        Runnable runnablePrice = new RunnablePrice(transportSynchronizer);

        Thread threadNames = new Thread(runnableNames);
        Thread threadPrice = new Thread(runnablePrice);

        threadNames.start();
        threadPrice.start();
    }

    public static void Task3(){
        Transport auto = new Auto("BMW", 220);
        ReentrantLock lock = new ReentrantLock();

        Runnable runnableNames = new RunableReentrantLockNames(auto, lock);
        Runnable runnablePrice = new RunableReentrantLockPrice(auto, lock);

        Thread threadNames = new Thread(runnableNames);
        Thread threadPrice = new Thread(runnablePrice);

        threadNames.start();
        threadPrice.start();
    }

    public static void Task4(){
        Transport auto = new Auto("BMW", 2);
        Transport motorbike = new Motorbike("Yamaha", 2);
        Transport scooter = new Scooter("Alpha", 2);
        Transport moped = new Moped("Joker", 2);

        Runnable runnableAuto = new RunableBrand(auto);
        Runnable runnableMotorbike = new RunableBrand(motorbike);
        Runnable runnableScooter = new RunableBrand(scooter);
        Runnable runnableMoped = new RunableBrand(moped);

        ExecutorService pool = Executors.newFixedThreadPool(2);

        pool.submit(runnableAuto);
        pool.submit(runnableMotorbike);
        pool.submit(runnableScooter);
        pool.submit(runnableMoped);

        pool.shutdown();
    }

    public static void Task5(){
        String[] file = {"auto1.txt", "auto2.txt", "auto3.txt", "auto4.txt", "auto5.txt"};
        ArrayBlockingQueue<Transport> arrayBlockingQueue = new ArrayBlockingQueue<Transport>(1);

        for (String f : file){
            Runnable runnable = new RunableReadFile(f, arrayBlockingQueue);
            (new Thread(runnable)).start();
        }

        for (int i = 0; i < file.length; i++){
            Transport transport = null;
            try {
                transport = arrayBlockingQueue.take();
                System.out.println(transport.getBrand());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
