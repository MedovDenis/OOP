package Thread;

import Interface.*;

public class TransportSynchronizer {
    private Transport transport;
    private volatile int current = 0;
    private Object lock = new Object();
    private boolean set = false;

    public TransportSynchronizer(Transport transport) {
        this.transport = transport;
    }

    public void printPrice() throws InterruptedException {
        double val = 0;
        double [] priceModels = transport.getPriceModels();

        synchronized(lock) {
            if (!canPrintPrice()) throw new InterruptedException();
            while (!set)
                lock.wait();
            System.out.println("Print price: " + priceModels[current]);
            set = false;
            lock.notifyAll();
            current += 1;
        }
    }

    public void printModel() throws InterruptedException {
        String [] nameModels = transport.getNameModels();

        synchronized(lock) {
            if (!canPrintModel()) throw new InterruptedException();
            while (set)
                lock.wait();
            System.out.println("Print model: " + nameModels[current]);
            set = true;
            lock.notifyAll();
        }

    }

    public boolean canPrintPrice() {
        return current < transport.getCountModel();
    }

    public boolean canPrintModel() {
        return (!set && current < transport.getCountModel()) || (set && current < transport.getCountModel() - 1);
    }
}
