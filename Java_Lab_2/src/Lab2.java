public class Lab2 {

    public static void main (String[] args){
        Transport motorbike = new Motorbike("Yamaha", 10);
        Transport auto = new Auto("Mazda", 15);

        Transports.printModels(motorbike);
        Transports.printModels(auto);

    }
}
