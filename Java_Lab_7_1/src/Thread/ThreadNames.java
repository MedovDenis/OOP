package Thread;

import Interface.Transport;
import Transport.Transports;

public class ThreadNames extends Thread{
    private Transport transport;

    public ThreadNames(Transport transport){
        this.transport = transport;
    }

    public void run () {
        Transports.printNameModels(transport);
    }
}
