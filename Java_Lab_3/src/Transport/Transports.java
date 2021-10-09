package Transport;

import Interface.*;
import Exception.*;

import java.io.*;

public class Transports {

    public static double getAveragePrice (Transport transport){
        double[] price = transport.getPriceModels();
        double mean = 0;

        for( double p : price ){
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

    public static void outputTransport (Transport v, OutputStream out)
            throws IOException, NoSuchModelNameException{
        if(out == null) throw new IOException();

        DataOutputStream dataOutputStream = new DataOutputStream(out);

        byte[] type = v.getType().getBytes();
        dataOutputStream.writeInt(type.length);
        dataOutputStream.write(type);

        byte[] brand = v.getBrand().getBytes();
        dataOutputStream.writeInt(brand.length);
        dataOutputStream.write(brand);

        dataOutputStream.writeInt(v.getCountModel());

        for(String modelName : v.getNameModels()){
            byte[]  name = modelName.getBytes();
            dataOutputStream.writeInt(name.length);
            dataOutputStream.write(name);

            dataOutputStream.writeDouble(v.getPriceModel(modelName));
        }

        dataOutputStream.close();
    }

    public static Transport inputTransport (InputStream in)
            throws IOException, DuplicateModelNameException{
        if(in == null) throw new IOException();

        Transport transport = null;
        DataInputStream dataInputStream = new DataInputStream(in);

        int typeLength = dataInputStream.readInt();
        byte[] type = new byte[typeLength];

        for (int i = 0; i < typeLength; i++){
            type[i] = dataInputStream.readByte();
        }

        int brandLength = dataInputStream.readInt();
        byte[] brand = new byte[brandLength];

        for (int i = 0; i < brandLength; i++){
            brand[i] = dataInputStream.readByte();
        }

        switch (new String(type)){
            case "Auto":
                transport = new Auto(new String(brand));
                break;
            case "Motorbike":
                transport = new Motorbike(new String(brand));
                break;
        }

        int countModels = dataInputStream.readInt();
        for(int i = 0; i < countModels; i++){
            int nameLength = dataInputStream.readInt();
            byte[] name = new byte[nameLength];
            for (int j = 0; j < nameLength; j++){
                name[j] = dataInputStream.readByte();
            }

            double price = dataInputStream.readDouble();

            transport.addModel(new String(name), price);
        }

        dataInputStream.close();
        return transport;
    }

    public static void writeTransport (Transport v, Writer out)
            throws IOException, NoSuchModelNameException{
        if (out == null) throw new IOException();

        PrintWriter printWriter = new PrintWriter(out);

        printWriter.println(v.getType());
        printWriter.println(v.getBrand());
        printWriter.println(v.getCountModel());

        for(String model : v.getNameModels()){
            printWriter.println(model);
            printWriter.println(v.getPriceModel(model));
        }

        printWriter.close();
    }

    public static Transport readTransport (Reader in)
            throws IOException, DuplicateModelNameException{
        if(in == null) throw new IOException();

        Transport transport = null;

        BufferedReader bufferedReader = new BufferedReader(in);

        switch (bufferedReader.readLine()){
            case "Auto":
                transport = new Auto(bufferedReader.readLine());
                break;
            case "Motorbike":
                transport = new Motorbike(bufferedReader.readLine());
                break;
        }

        int countModels = Integer.parseInt(bufferedReader.readLine());

        for(int i = 0; i < countModels; i++){
            String nameModel = bufferedReader.readLine();
            double price = Double.parseDouble(bufferedReader.readLine());
            transport.addModel(nameModel, price);
        }

        bufferedReader.close();
        return transport;
    }

}
