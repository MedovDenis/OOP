public class Lab2 {

    public static void main (String[] args){
        Motorbike auto = new Motorbike("Mazda", 10);

        String massName[] = auto.getNameModels();
        double massPrice[] = auto.getPriceModels();

        for (String name : massName){
            System.out.println(name);
        }

//        for (double price : massPrice){
//            System.out.println(price);
//        }

        System.out.println("");

        auto.delModel("Moto 4");

        for (String name : auto.getNameModels()){
            System.out.println(name);
        }

    }
}
