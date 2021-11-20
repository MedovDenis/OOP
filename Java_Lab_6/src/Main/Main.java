package Main;

import Interface.*;
import Transport.*;
import Exception.*;

import java.io.*;
import  java.lang.reflect.*;

public class Main {

        public static void main (String[] args) throws DuplicateModelNameException {
            // Задание 1
            System.out.println("Задание 1");
            Transport auto = new Auto("Mazda", 2);
            System.out.println(auto);
            System.out.println();

            try{
                Class c = Class.forName(args[0]);
                Method m = c.getMethod(args[1], new Class[] {String.class, Double.TYPE});
                String name = args[2];
                Integer price = Integer.valueOf(args[3]);
                Object res = m.invoke(auto, name, price);
            }
            catch (ClassNotFoundException e) {
                System.out.println("Класс не найден");
            }
            catch (NoSuchMethodException e) {
                System.out.println("Метод не найден");
            }
            catch (IllegalAccessException e) {
                System.out.println("Метод недоступен");
            }
            catch (InvocationTargetException e) {
                System.out.println("При вызове возникло исключение");
            }
            catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Выход за границы массива");
            }

            System.out.println(auto);
            System.out.println();


            // Задание 2
            System.out.println("Задание 2");
            Transport auto1 = Transports.createTransport("Mashinka", 2, auto);
            System.out.println(auto1);
            System.out.println();
//            Transports.printModels(newAuto);

            // Задание 3
            System.out.println("Задание 3");
            Transport scooter = new Scooter("Suzuki", 2);
            System.out.println(scooter);
            scooter.addModel("Scooter333", 999);
            System.out.println();
            System.out.println(scooter);
            System.out.println();


            // Задание 4
            System.out.println("Задание 4");
            Transport quadbike = new Quadbike("Yamaha", 2);
            System.out.println(quadbike);
            quadbike.addModel("Quadbike333", 999);
            System.out.println();
            System.out.println(quadbike);
            System.out.println();

            // Задание 5
            System.out.println("Задание 5");
            Transport moped = new Moped("Alpha", 2);
            System.out.println(moped);
            moped.addModel("Moped333", 999);
            System.out.println();
            System.out.println(moped);
            System.out.println();

            //Задание 6
            System.out.println("Задание 6");
            Transport[] transports = {auto, auto1, scooter, quadbike, moped};
            System.out.println( Transports.getAvaragePriceTransports(transports));
            System.out.println();


            //Задание 7
            File file = null;
            file = new File("file");

            Writer writer = null;
            try{
                if (file == null) writer = new OutputStreamWriter(System.out);
                else writer = new FileWriter(file);

                Transports.writeTransport(auto, writer);
            }
            catch (IOException | NoSuchModelNameException e){
                System.out.println(e);
            }
            finally {
                try{
                    writer.close();
                }
                catch (IOException e) {
                    System.out.println(e);
                }
            }


            System.out.println("Задание 7");
            Reader reader = null;
            try{
                if (file == null) reader = new InputStreamReader(System.in);
                else reader = new FileReader(file);

                Transport transport = Transports.readTransport(reader);

                System.out.println(transport.getBrand());
                Transports.printModels(transport);
            }
            catch (IOException | DuplicateModelNameException e){
                System.out.println(e);
            }
            finally {
                try{
                    reader.close();
                }
                catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
}
