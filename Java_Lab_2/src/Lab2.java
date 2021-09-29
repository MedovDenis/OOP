public class Lab2 {

    public static void main (String[] args){
        Transport auto = new Motorbike("Mazda", 10);

        String massName[] = auto.getNameModels();
        double massPrice[] = auto.getPriceModels();

        for (String name : massName){
            System.out.println(name);
        }

        for (double price : massPrice){
            System.out.println(price);
        }
        try{
            auto.delModel("Moto 4");
        } catch (NoSuchModelNameException noSuchModelNameException){

        }

        for (String name : auto.getNameModels()){
            System.out.println(name);
        }

    }
}
