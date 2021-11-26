package Thread;

import Interface.Transport;

public class RunableBrand implements Runnable {
    private Transport transport;

    public RunableBrand (Transport transport){
        this.transport = transport;
    }

    public void run(){
        System.out.println(transport.getBrand());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
