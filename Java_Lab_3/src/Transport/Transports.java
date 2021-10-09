package Transport;

import Interface.*;

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

}
