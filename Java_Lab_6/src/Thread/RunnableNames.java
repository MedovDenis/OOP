package Thread;

public class RunnableNames implements Runnable {
    private TransportSynchronizer transportSynchronizer;

    public RunnableNames(TransportSynchronizer transportSynchronizer){
        this.transportSynchronizer = transportSynchronizer;
    }

    public void run(){
        try {
            while (transportSynchronizer.canPrintModel()){
                transportSynchronizer.printModel();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
