public class Lab2 {

    public static void main (String[] args){

        Transport motorbike = new Motorbike("Yamaha", 10);
        Transport auto = new Auto("Mazda", 15);

        System.out.println("Проверка транспорта:" + motorbike.getBrand() + "\n");
        Transports.printModels(motorbike);
        System.out.println("Average price: " + Transports.getAveragePrice(motorbike) + "\n");

        try{
            System.out.println(motorbike.getPriceModel("Moto 1"));
            System.out.println(motorbike.getPriceModel("Moto 333"));
        }
        catch (NoSuchModelNameException e){
            System.out.println(e.getMessage() + "\n");
        }

        try{
            motorbike.setPriceModel("Moto 1", 333.2);
            System.out.println(motorbike.getPriceModel("Moto 1"));

            motorbike.setPriceModel("Moto 333", 333.2);
            System.out.println(motorbike.getPriceModel("Moto 333"));
        }
        catch (NoSuchModelNameException e){
            System.out.println(e.getMessage()+ "\n");
        }

        try{
            motorbike.setPriceModel("Moto 1", 444.3);
            System.out.println(motorbike.getPriceModel("Moto 1"));

            motorbike.setPriceModel("Moto 333", -321.2);
            System.out.println(motorbike.getPriceModel("Moto 333"));
        }
        catch (ModelPriceOutOfBoundsException | NoSuchModelNameException e){
            System.out.println(e.getMessage()+ "\n");
        }

        try{
            motorbike.addModel("Moto 333", 333.2);
            System.out.println(motorbike.getPriceModel("Moto 333"));

            motorbike.addModel("Moto 1", 444.3);
            System.out.println(motorbike.getPriceModel("Moto 1"));
        }
        catch (DuplicateModelNameException | NoSuchModelNameException e){
            System.out.println(e.getMessage()+ "\n");
        }

        try{
            motorbike.addModel("Moto 444", 333.2);
            System.out.println(motorbike.getPriceModel("Moto 444"));

            motorbike.setPriceModel("Moto 888", -444.3);
            System.out.println(motorbike.getPriceModel("Moto 888"));
        }
        catch (DuplicateModelNameException | NoSuchModelNameException | ModelPriceOutOfBoundsException e){
            System.out.println(e.getMessage()+ "\n");
        }

        try{
            motorbike.delModel("Moto 1");
            motorbike.delModel("Moto 999");
        }
        catch (NoSuchModelNameException e){
            System.out.println(e.getMessage()+ "\n");
        }

        System.out.println("Проверка транспорта:" + auto.getBrand()+ "\n");
        Transports.printModels(auto);
        System.out.println("Average price: " + Transports.getAveragePrice(auto)+ "\n");

        try{
            System.out.println(auto.getPriceModel("Auto 1"));
            System.out.println(auto.getPriceModel("Auto 333"));
        }
        catch (NoSuchModelNameException e){
            System.out.println(e.getMessage()+ "\n");
        }

        try{
            auto.setPriceModel("Auto 1", 333.2);
            System.out.println(auto.getPriceModel("Auto 1"));

            auto.setPriceModel("Auto 333", 333.2);
            System.out.println(auto.getPriceModel("Auto 333"));
        }
        catch (NoSuchModelNameException e){
            System.out.println(e.getMessage()+ "\n");
        }

        try{
            auto.setPriceModel("Auto 1", 444.3);
            System.out.println(auto.getPriceModel("Auto 1"));

            auto.setPriceModel("Auto 333", -321.2);
            System.out.println(auto.getPriceModel("Auto 333"));
        }
        catch (ModelPriceOutOfBoundsException | NoSuchModelNameException e){
            System.out.println(e.getMessage()+ "\n");
        }

        try{
            auto.addModel("Auto 333", 333.2);
            System.out.println(auto.getPriceModel("Auto 1"));

            auto.addModel("Auto 1", 444.3);
            System.out.println(auto.getPriceModel("Auto 1"));
        }
        catch (DuplicateModelNameException | NoSuchModelNameException e){
            System.out.println(e.getMessage()+ "\n");
        }

        try{
            auto.addModel("Auto 444", 333.2);
            System.out.println(auto.getPriceModel("Auto 444"));

            auto.setPriceModel("Auto 888", -444.3);
            System.out.println(auto.getPriceModel("Auto 888"));
        }
        catch (DuplicateModelNameException | NoSuchModelNameException | ModelPriceOutOfBoundsException e){
            System.out.println(e.getMessage()+ "\n");
        }

        try{
            auto.delModel("Auto 1");
            auto.delModel("Auto 999");
        }
        catch (NoSuchModelNameException e){
            System.out.println(e.getMessage()+ "\n");
        }


    }
}
