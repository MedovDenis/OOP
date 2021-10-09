package Main;
import Interface.*;
import Transport.*;
import Exception.*;
import com.sun.source.tree.TryTree;

import java.io.*;
import java.net.StandardSocketOptions;

public class Main {

    public static void main (String[] args){

        Transport motorbike = new Motorbike("Yamaha", 10);
        Transport auto = new Auto("Mazda", 15);

        File file = new File("file");

        OutputStream out;
        try {
            if (file == null){
                out = System.out;
            }
            else {
                out = new FileOutputStream(file);
            }

            Transports.outputTransport(auto, out);

            out.close();
        }
        catch (IOException e){
            System.out.println(e);
        }
        catch (NoSuchModelNameException e){
            System.out.println(e);
        }

        InputStream in;
        try{
            if (file ==  null){
                in = System.in;
            }
            else {
                in = new FileInputStream(file);
            }

            Transport transport = Transports.inputTransport(in);

            System.out.println(transport.getBrand());
            Transports.printModels(transport);

            in.close();
        }
        catch (IOException e){
            System.out.println(e);
        }
        catch (DuplicateModelNameException e){
            System.out.println(e);
        }

        /*
        System.out.println("Транспорт:" + motorbike.getBrand() + "\n");
        Transports.printModels(motorbike);
        System.out.println("Средняя цена: " + Transports.getAveragePrice(motorbike) + "\n");

        try{
            motorbike.setNameModel("Moto 5", "Moto 555");

            System.out.println(motorbike.getPriceModel("Moto 555"));

            motorbike.setNameModel("Moto 321", "Moto 123");
            System.out.println(motorbike.getPriceModel("Moto 123"));
        }
        catch (DuplicateModelNameException | NoSuchModelNameException e){
            System.out.println(e.getMessage()+ "\n");
        }

        try{
            motorbike.setNameModel("Moto 555", "Moto 5");
            System.out.println(motorbike.getPriceModel("Moto 5"));

            motorbike.setNameModel("Moto 9", "Moto 8");
            System.out.println(motorbike.getPriceModel("Moto 1"));
        }
        catch (DuplicateModelNameException | NoSuchModelNameException e){
            System.out.println(e.getMessage()+ "\n");
        }

        try{
            System.out.println(motorbike.getPriceModel("Moto 1"));
            System.out.println(motorbike.getPriceModel("Moto 333"));
        }
        catch (NoSuchModelNameException e){
            System.out.println(e.getMessage() + "\n");
        }

        try{
            motorbike.setPriceModel("Moto 1", 333.2);
            System.out.println(motorbike.getPriceModel("Moto 1"));

            motorbike.setPriceModel("Moto 333", 333.2);
            System.out.println(motorbike.getPriceModel("Moto 333"));
        }
        catch (NoSuchModelNameException e){
            System.out.println(e.getMessage()+ "\n");
        }

        try{
            motorbike.setPriceModel("Moto 1", 444.3);
            System.out.println(motorbike.getPriceModel("Moto 1"));

            motorbike.setPriceModel("Moto 333", -321.2);
            System.out.println(motorbike.getPriceModel("Moto 333"));
        }
        catch (ModelPriceOutOfBoundsException | NoSuchModelNameException e){
            System.out.println(e.getMessage()+ "\n");
        }

        try{
            motorbike.addModel("Moto 333", 333.2);
            System.out.println(motorbike.getPriceModel("Moto 333"));

            motorbike.addModel("Moto 1", 444.3);
            System.out.println(motorbike.getPriceModel("Moto 1"));
        }
        catch (DuplicateModelNameException | NoSuchModelNameException e){
            System.out.println(e.getMessage()+ "\n");
        }

        try{
            motorbike.addModel("Moto 444", 333.2);
            System.out.println(motorbike.getPriceModel("Moto 444"));

            motorbike.setPriceModel("Moto 888", -444.3);
            System.out.println(motorbike.getPriceModel("Moto 888"));
        }
        catch (DuplicateModelNameException | NoSuchModelNameException | ModelPriceOutOfBoundsException e){
            System.out.println(e.getMessage()+ "\n");
        }

        try{
            motorbike.delModel("Moto 2", 200000);
            motorbike.delModel("Moto 999",99900000);
        }
        catch (NoSuchModelNameException e){
            System.out.println(e.getMessage()+ "\n");
        }

        System.out.println("Трнаспорт:" + auto.getBrand()+ "\n");
        Transports.printModels(auto);
        System.out.println("Средняя цена: " + Transports.getAveragePrice(auto)+ "\n");

        try{
            auto.setNameModel("Auto 5", "Auto 555");

            System.out.println(auto.getPriceModel("Auto 555"));

            auto.setNameModel("Auto 321", "Auto 123");
            System.out.println(auto.getPriceModel("Auto 123"));
        }
        catch (DuplicateModelNameException | NoSuchModelNameException e){
            System.out.println(e.getMessage()+ "\n");
        }

        try{
            auto.setNameModel("Auto 555", "Auto 5");
            System.out.println(auto.getPriceModel("Auto 5"));

            auto.setNameModel("Auto 321", "Auto 1");
            System.out.println(auto.getPriceModel("Auto 1"));
        }
        catch (DuplicateModelNameException | NoSuchModelNameException e){
            System.out.println(e.getMessage()+ "\n");
        }

        try{
            System.out.println(auto.getPriceModel("Auto 1"));
            System.out.println(auto.getPriceModel("Auto 333"));
        }
        catch (NoSuchModelNameException e){
            System.out.println(e.getMessage()+ "\n");
        }

        try{
            auto.setPriceModel("Auto 1", 333.2);
            System.out.println(auto.getPriceModel("Auto 1"));

            auto.setPriceModel("Auto 333", 333.2);
            System.out.println(auto.getPriceModel("Auto 333"));
        }
        catch (NoSuchModelNameException e){
            System.out.println(e.getMessage()+ "\n");
        }

        try{
            auto.setPriceModel("Auto 1", 444.3);
            System.out.println(auto.getPriceModel("Auto 1"));

            auto.setPriceModel("Auto 333", -321.2);
            System.out.println(auto.getPriceModel("Auto 333"));
        }
        catch (ModelPriceOutOfBoundsException | NoSuchModelNameException e){
            System.out.println(e.getMessage()+ "\n");
        }

        try{
            auto.addModel("Auto 333", 333.2);
            System.out.println(auto.getPriceModel("Auto 1"));

            auto.addModel("Auto 1", 444.3);
            System.out.println(auto.getPriceModel("Auto 1"));
        }
        catch (DuplicateModelNameException | NoSuchModelNameException e){
            System.out.println(e.getMessage()+ "\n");
        }

        try{
            auto.addModel("Auto 444", 333.2);
            System.out.println(auto.getPriceModel("Auto 444"));

            auto.setPriceModel("Auto 888", -444.3);
            System.out.println(auto.getPriceModel("Auto 888"));
        }
        catch (DuplicateModelNameException | NoSuchModelNameException | ModelPriceOutOfBoundsException e){
            System.out.println(e.getMessage()+ "\n");
        }

        try{
            auto.delModel("Auto 2", 200000);
            auto.delModel("Auto 999", 99900000.0);
        }
        catch (NoSuchModelNameException e){
            System.out.println(e.getMessage()+ "\n");
        }
         */


    }
}
