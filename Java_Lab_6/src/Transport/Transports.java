package Transport;

import Interface.*;
import Exception.*;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Transports {

    public static double getAveragePrice (Transport transport){
        double[] price = transport.getPriceModels();
        double mean = 0;

        for(double p : price ){
            mean += p;
        }
        return mean / price.length;
    }

    public static void printNameModels (Transport transport){
        String[] name = transport.getNameModels();

        for(int i = 0; i < name.length; i++ ){
            System.out.println(name[i]);
        }
    }

    public static void printPriceModels (Transport transport){
        double[] price = transport.getPriceModels();

        for(int i = 0; i < price.length; i++ ){
            System.out.println(price[i]);
        }
    }

    public static void printModels (Transport transport){
        String[] name = transport.getNameModels();
        double[] price = transport.getPriceModels();

        for(int i = 0; i < price.length; i++ ){
            System.out.println(name[i] + " : " + price[i]);
        }
    }

    private static void fromStringToStraem(String data, DataOutputStream dataOutputStream)
            throws IOException{
        byte[] byteData = data.getBytes();
        dataOutputStream.writeInt(byteData.length);
        dataOutputStream.write(byteData);
    }

    public static void outputTransport (Transport v, OutputStream out)
            throws IOException, NoSuchModelNameException{

        DataOutputStream dataOutputStream = new DataOutputStream(out);

        fromStringToStraem(v.getType(), dataOutputStream);
        fromStringToStraem(v.getBrand(), dataOutputStream);
        dataOutputStream.writeInt(v.getCountModel());

        for(String modelName : v.getNameModels()){
            fromStringToStraem(modelName, dataOutputStream);
            dataOutputStream.writeDouble(v.getPriceModel(modelName));
        }

        dataOutputStream.flush();
    }

    private static String fromStreamToString(DataInputStream dataInputStream)
            throws IOException{
        int length = dataInputStream.readInt();
        byte[] data = new byte[length];

        for (int i = 0; i < length; i++){
            data[i] = dataInputStream.readByte();
        }

        return new String(data);
    }

    public static Transport inputTransport (InputStream in)
            throws IOException, DuplicateModelNameException{

        Transport transport = null;
        DataInputStream dataInputStream = new DataInputStream(in);

        switch (fromStreamToString(dataInputStream)){
            case "Auto":
                transport = new Auto(fromStreamToString(dataInputStream));
                break;
            case "Motorbike":
                transport = new Motorbike(fromStreamToString(dataInputStream));
                break;
        }

        int countModels = dataInputStream.readInt();
        for(int i = 0; i < countModels; i++){
            transport.addModel(
                    fromStreamToString(dataInputStream),
                    dataInputStream.readDouble());
        }
        
        return transport;
    }

    public static void writeTransport (Transport v, Writer out)
            throws IOException, NoSuchModelNameException{

        PrintWriter printWriter = new PrintWriter(out);

        printWriter.printf("%s%n", v.getType());
        printWriter.printf("%s%n", v.getBrand());
        printWriter.printf("%d%n", v.getCountModel());

        for(String model : v.getNameModels()){
            printWriter.printf("%s%n", model);
            printWriter.printf("%.2f%n", v.getPriceModel(model));
        }

        printWriter.flush();
    }

    public static Transport readTransport (Reader in)
            throws IOException, DuplicateModelNameException{

        Transport transport = null;
        Scanner scanner = new Scanner(in);

        switch (scanner.nextLine()){
            case "Auto":
                transport = new Auto(scanner.nextLine());
                break;
            case "Motorbike":
                transport = new Motorbike(scanner.nextLine());
                break;
            case "Scooter":
                transport = new Scooter(scanner.nextLine());
                break;
            case "Quadbike":
                transport = new Quadbike(scanner.nextLine());
                break;
            case "Moped":
                transport = new Moped(scanner.nextLine());
                break;
            default:
                throw new IOException();
        }

        int countModels = Integer.parseInt(scanner.nextLine());

        for(int i = 0; i < countModels; i++){
            String nameModel = scanner.nextLine();
            double price = Double.parseDouble(scanner.nextLine().replace(",", "."));
            transport.addModel(nameModel, price);
        }

        scanner.close();
        return transport;
    }

    public static Transport createTransport(String brand, int count, Transport transport){
        Object res = null;
        try{
            Class c = transport.getClass();
            Constructor cons = c.getConstructor(new Class[] {String.class, Integer.TYPE});
            Integer count_ = Integer.valueOf(count);
            res = cons.newInstance(brand, count_);
            return (Transport) res;
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            return null;
        }
    }

    public static double getAvaragePriceTransports(Transport ... transports){
        double summ = 0;
        int count = 0;
        for(Transport transport : transports){
            double[] prices = transport.getPriceModels();
            for(double price : prices){
                summ += price;
                count ++;
            }
        }
        return summ / count;
    }

}
