public class Transports {

    public static double getAveragePrice (Transport transport){
        double[] price = transport.getPriceModels();
        double avarage = 0;

        for( double p : price ){
            avarage += p;
        }
        return avarage / price.length;
    }

    public static void printModels (Transport transport){
        String[] name = transport.getNameModels();
        double[] price = transport.getPriceModels();

        for(int i = 0; i < price.length; i++ ){
            System.out.println(name[i] + " : " + price[i]);
        }
    }

}
