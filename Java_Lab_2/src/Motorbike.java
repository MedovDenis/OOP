import java.util.Arrays;

public class Motorbike {

    private String brand;
    private int size = 0;
    private Model head = new Model();{
        head.prev = head;
        head.next = head;
    }

    //  метод для получения марки автомобиля
    public String getBrand() { return brand; }

    //  метод для модификации марки автомобиля
    public void setBrand(String brand){
        this.brand = brand;
    }

    //  метод, возвращающий массив названий всех моделей
    public String[] getNameModels(){

    }

    //  метод для получения значения цены модели по её названию
    public double getPriceModel(String name){

    }

    //  метод для модификации значения цены модели по её названию,
    public void setPriceModel(String name, double price){

    }

    //  метод для получения массива цен моделей
    public double[] getPriceModels(){

    }

    //  метод для добавления модели в массив моделей
    public void addModel(String name, double price){

    }

    public void delModel(String name){

    }

    public int findModel(String name, double price){

    }

    public int findModel(String name){

    }


    public int getCountModel(){ return models.length; }

    public Motorbike(String brand, int countBrand){

    }

    private class Model{
        String name = null;
        double price = Double.NaN;
        Model prev = null;
        Model next = null;
    }



}
