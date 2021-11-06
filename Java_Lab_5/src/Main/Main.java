package Main;

import Interface.*;
import Transport.*;
import Exception.*;

import java.io.*;
import  java.lang.reflect.*;

public class Main {

        public static void main (String[] args){

            Transport auto = new Auto("Mazda", 2);
            Transports.printModels(auto);

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
            Transports.printModels(auto);

            Transport newAuto = Transports.createTransport("Mashinka", 5, auto);

            Transports.printModels(newAuto);
        }
}
