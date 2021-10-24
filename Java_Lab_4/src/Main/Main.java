package Main;
import Interface.*;
import Transport.*;
import Exception.*;

import java.io.*;

public class Main {

    public static void main (String[] args){

        Transport motorbike = new Motorbike("Yamaha", 10);
        Transport auto = new Auto("Mazda", 15);

        System.out.println(motorbike.toString());
        System.out.println(auto.toString());

//        System.out.println("Оригинал:");
//        System.out.println(motorbike.getBrand());
//        Transports.printModels(motorbike);
//        System.out.println(auto.getBrand());
//        Transports.printModels(auto);
//
//        File file = null;
//        file = new File("file");
//
//        OutputStream out = null;
//        try {
//            if (file == null) out = System.out;
//            else out = new FileOutputStream(file);
//
//            Transports.outputTransport(auto, out);
//
//            out.close();
//        }
//        catch (IOException | NoSuchModelNameException e){
//            System.out.println(e);
//        }
//        finally {
//            try {
//                out.close();
//            }
//            catch (IOException e){
//                System.out.println(e);
//            }
//        }
//
//
//        InputStream in = null;
//        try{
//            if (file ==  null) in = System.in;
//            else in = new FileInputStream(file);
//
//            Transport transport = Transports.inputTransport(in);
//
//            System.out.println("Байтовый поток:");
//            System.out.println(transport.getBrand());
//            Transports.printModels(transport);
//        }
//        catch (IOException | DuplicateModelNameException e){
//            System.out.println(e);
//        }
//        finally {
//            try {
//                in.close();
//            }
//            catch (IOException e){
//                System.out.println(e);
//            }
//        }
//
//
//        Writer writer = null;
//        try{
//            if (file == null) writer = new OutputStreamWriter(System.out);
//            else writer = new FileWriter(file);
//            Transports.writeTransport(motorbike, writer);
//        }
//        catch (IOException | NoSuchModelNameException e){
//            System.out.println(e);
//        }
//        finally {
//            try{
//                writer.close();
//            }
//            catch (IOException e) {
//                System.out.println(e);
//            }
//        }
//
//
//        Reader reader = null;
//        try{
//            if (file == null) reader = new InputStreamReader(System.in);
//            else reader = new FileReader(file);
//
//            Transport transport = Transports.readTransport(reader);
//
//            System.out.println("Символьный поток:");
//            System.out.println(transport.getBrand());
//            Transports.printModels(transport);
//        }
//        catch (IOException | DuplicateModelNameException e){
//            System.out.println(e);
//        }
//        finally {
//            try{
//                reader.close();
//            }
//            catch (IOException e) {
//                System.out.println(e);
//            }
//        }
//
//        try{
//            FileOutputStream fileOut = new FileOutputStream(file);
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOut);
//            objectOutputStream.writeObject(motorbike);
//        }
//        catch (IOException e){
//            System.out.println("write " + e);
//        }
//
//        try{
//            FileInputStream fileIn = new FileInputStream(file);
//            ObjectInputStream objectInputStream = new ObjectInputStream(fileIn);
//            Transport transport = (Transport) objectInputStream.readObject();
//
//            System.out.println("Сериализация:");
//            System.out.println(transport.getBrand());
//            Transports.printModels(transport);
//
//        }
//        catch (IOException | ClassNotFoundException e){
//            System.out.println("read " + e);
//        }
//
//        try{
//            Transport  t = Transports.readTransport( new InputStreamReader(System.in));
//            Transports.writeTransport(t, new OutputStreamWriter(System.out));
//
//            System.out.println("Что нибудь");
//        }
//        catch (IOException | DuplicateModelNameException | NoSuchModelNameException e){
//            System.out.println(e);
//        }

    }
}
