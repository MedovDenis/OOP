package Thread;

import Interface.Transport;

public class TransportSynchronizer {
    private Transport transport;
    private volatile int current = 0;
    private Object lock = new Object();
    private boolean set = true;

    public TransportSynchronizer(Transport transport) {
        this.transport = transport;
    }

    public double printPrice() throws InterruptedException {
        double val;
        synchronized (lock) {
            double[] p = transport.getPriceModels();
            if (!canPrintPrice()) throw new InterruptedException();
            while (!set) {
                lock.wait();
            }
            val = p[current++];
            System.out.println("Print price: " + val);
            set = false;
            lock.notifyAll();
        }
        return val;
    }

    public void printModel() throws InterruptedException {
        synchronized (lock) {
            String[] s = transport.getNameModels();
            if (!canPrintModel()) throw new InterruptedException();
            while (set) {
                lock.wait();
            }
            System.out.println("Print model: " + s[current]);
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

