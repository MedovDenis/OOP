import java.util.Arrays;

public class Auto {
    private String brand;
    private Model[] models;

//  метод для получения марки автомобиля
    public String getBrand() { return brand; }

//  метод для модификации марки автомобиля
    public void setBrand(String brand){
        this.brand = brand;
    }

//  метод, возвращающий массив названий всех моделей
    public String[] getNameModels(){
        int len = getCountModel();
        String[] nameModels = new String[len];

        for (int i = 0; i < len; i++){
            nameModels[i] = models[i].getName();
        }

        return nameModels;
    }

//  метод для получения значения цены модели по её названию
    public double getPriceModel(String name){
        double price = Double.NaN;
        String[] nameModels = getNameModels();

        for(int i = 0; i < getCountModel(); i++){
            if (nameModels[i] == name){
                price = models[i].getPrice();
            }
        }
        return price;
    }

//  метод для модификации значения цены модели по её названию,
    public void setPriceModel(String name, double price){
        String[] nameModels = getNameModels();

        for(int i = 0; i < getCountModel(); i++){
            if (nameModels[i] == name){
                models[i].setPrice(price);
            }
        }
    }

//  метод для получения массива цен моделей
    public double[] getPriceModels(){
        int len = getCountModel();
        double[] priceModels = new double[len];

        for(int i = 0; i < len; i++){
            priceModels[i] = models[i].getPrice();
        }

        return priceModels;
    }

//  метод для добавления модели в массив моделей
    public void addModel(String name, double price){
        models = Arrays.copyOf(models, getCountModel() + 1 );
        Model newModel = new Model(name, price);
        models[getCountModel() - 1] = newModel;
    }

    public void delModel(String name){
        int index = findModel(name);

        if (index >= 0) {
            System.arraycopy(models, index + 1, models, index, getCountModel() - index - 1);
            models = Arrays.copyOf(models, getCountModel() - 1);
        }
    }

    public int findModel(String name, double price){
        int len = getCountModel();
        int index = -1;
        int i = 0;

        while(i < len){
            if (models[i].getName().equals(name) && models[i].getPrice() == price){
                index = i;
                break;
            }
            i++;
        }

        return index;
    }

    public int findModel(String name){
        int len = getCountModel();
        int index = -1;
        int i = 0;

        while(i < len){
            if (models[i].getName().equals(name)){
                index = i;
                break;
            }
            i++;
        }

        return index;
    }


    public int getCountModel(){ return models.length; }

    public Auto(String brand, int countBrand){
        this.brand = brand;
        models = new Model[countBrand];

        for (int i = 0; i < countBrand; i++){
            String name = "Auto " + (i + 1);
            double price = Math.random() * 1000000;
            models[i] = new Model(name, price);
        }
    }

    class Model{
        private String name = null;
        private double price = Double.NaN;

//      метод для модификации значения названия модели,
        public void setName(String name){
            this.name = name;
        }

//      метод для получения названия модели
        public String getName(){
            return name;
        }

//      метод для модификации цены модели
        public void setPrice(double price){
            this.price = price;
        }

//      метод для получения цены модели
        public double getPrice(){
            return price;
        }

        public Model(String name, double price){
            this.name = name;
            this.price = price;
        }
    }

}
