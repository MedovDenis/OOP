package Thread;

import Interface.Transport;
import Transport.Transports;
import java.util.concurrent.locks.ReentrantLock;

public class RunableReentrantLockNames implements Runnable {
    private Transport transport;
    private ReentrantLock lock;

    public RunableReentrantLockNames(Transport transport, ReentrantLock lock){
        this.transport = transport;
        this.lock = lock;
    }

    public void run(){
        //assert lock.isHeldByCurrentThread();
        lock.lock();
        try {
            Transports.printNameModels(transport);
        } finally {
            lock.unlock();
        }
    }
}
