package Thread;

import Interface.Transport;
import Transport.Transports;

import java.util.concurrent.locks.ReentrantLock;

public class RunableReentrantLockPrice implements Runnable {
    private Transport transport;
    private ReentrantLock lock;

    public RunableReentrantLockPrice(Transport transport, ReentrantLock lock){
        this.transport = transport;
        this.lock = lock;
    }

    public void run(){
        //assert lock.isHeldByCurrentThread();
        lock.lock();
        try {
            Transports.printPriceModels(transport);
        } finally {
            lock.unlock();
        }
    }

}
