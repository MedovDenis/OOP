public class Lab2 {

    public static void main (String[] args){
        Auto auto = new Auto("Mazda", 10);

        String massName[] = auto.getNameModels();
        double massPrice[] = auto.getPriceModels();

        for (String name : massName){
            System.out.println(name);
        }

        for (double price : massPrice){
            System.out.println(price);
        }
    }
}
