package Thread;

import Interface.Transport;
import Transport.Auto;
import java.io.*;
import java.util.concurrent.BlockingQueue;

public class RunableReadFile implements Runnable {
    String fileName;
    BlockingQueue blockingQueue;

    public RunableReadFile(String fileName, BlockingQueue blockingQueue){
        this.fileName = fileName;
        this.blockingQueue = blockingQueue;
    }

    public void run(){
        File file = new File(fileName);
        Reader reader = null;
        try {
            reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            Transport transport = new Auto(bufferedReader.readLine(), 2);
            //blockingQueue.put(transport);
            blockingQueue.add(transport);
        //} catch (IOException | InterruptedException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
