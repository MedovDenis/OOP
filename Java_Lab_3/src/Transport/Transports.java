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

        printWriter.println(v.getType());
        printWriter.println(v.getBrand());
        printWriter.println(v.getCountModel());

        for(String model : v.getNameModels()){
            printWriter.println(model);
            printWriter.println(v.getPriceModel(model));
        }

        printWriter.flush();
    }

    public static Transport readTransport (Reader in)
            throws IOException, DuplicateModelNameException{

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

        return transport;
    }

}
