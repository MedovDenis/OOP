package Main;
import Interface.*;
import Transport.*;
import Exception.*;

import java.io.*;

public class Main {

    public static void main (String[] args) throws DuplicateModelNameException, NoSuchModelNameException {

        Transport motorbike = new Motorbike("Yamaha", 2);
        Transport auto = new Auto("Mazda", 2);

        System.out.println("===== Motorbike =====");
//        System.out.println("1. toString:");
//        System.out.println(motorbike);
//        System.out.println();
//        System.out.println("2. hashCode:");
//        System.out.println(motorbike.hashCode());
        Motorbike motorbike2 = (Motorbike) motorbike.clone();

        motorbike2.setNameModel("Moto 1", "Поменялся ;)");
        System.out.println(motorbike);
        System.out.println();
        System.out.println(motorbike2);

//        System.out.println(motorbike2.hashCode());
//        System.out.println();
//        System.out.println("3. equals:");
//        System.out.println(motorbike.equals(motorbike2));
//        System.out.println();


        System.out.println("===== Auto =====");
//        System.out.println("1. toString:");
//        System.out.println(auto);
//        System.out.println();
//        System.out.println("2. hashCode:");
//        System.out.println(auto.hashCode());
        Auto auto2 = (Auto) auto.clone();
        auto2.setNameModel("Auto 1", "Поменялся ;)");
        System.out.println(auto);
        System.out.println();
        System.out.println(auto2);

//        System.out.println(auto2.hashCode());
//        System.out.println();
//        System.out.println("3. equals:");
//        System.out.println(auto.equals(auto2));
//        System.out.println();

    }
}
