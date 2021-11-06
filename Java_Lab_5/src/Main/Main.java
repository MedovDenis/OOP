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
                System.out.println(args[0]);
                Class c = Class.forName(args[0]);

                System.out.println(args[1]);
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

            }

            Transports.printModels(auto);

        }
}
