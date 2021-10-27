package Main;
import Interface.*;
import Transport.*;
import Exception.*;

import java.io.*;

public class Main {

    public static void main (String[] args){

        Transport motorbike = new Motorbike("Yamaha", 10);
        Transport auto = new Auto("Mazda", 15);

        System.out.println(motorbike.hashCode());
        Object motorbike2 = motorbike.clone();
        System.out.println(motorbike2.hashCode());
        System.out.println(motorbike.equals(motorbike2));

        System.out.println(auto.hashCode());
        Object auto2 = auto.clone();
        System.out.println(auto2.hashCode());
        System.out.println(auto.equals(auto2));

    }
}
