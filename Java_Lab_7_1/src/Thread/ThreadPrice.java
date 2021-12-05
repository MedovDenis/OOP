package Thread;

import Interface.Transport;
import Transport.Transports;

public class ThreadPrice extends Thread{
    private Transport transport;

    public ThreadPrice(Transport transport){
        this.transport = transport;
    }

    public void run () {
        Transports.printPriceModels(transport);
    }
}
