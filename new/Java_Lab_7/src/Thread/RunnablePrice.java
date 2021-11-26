package Thread;

public class RunnablePrice implements Runnable {
    private TransportSynchronizer transportSynchronizer;

    public RunnablePrice(TransportSynchronizer transportSynchronizer){
        this.transportSynchronizer = transportSynchronizer;
    }

    public void run(){
        try {
            while (transportSynchronizer.canPrintPrice()){
                transportSynchronizer.printPrice();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
