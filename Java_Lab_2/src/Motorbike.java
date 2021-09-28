import java.util.Arrays;

public class Motorbike {

    private String brand;
    private int size = 0;
    private Model head = new Model();{
        head.prev = head;
        head.next = head;
    }

    public String getBrand() { return brand; }

    public void setBrand(String brand){ this.brand = brand; }

    public String[] getNameModels(){
        String[] nameModels = new String[getCountModel()];
        Model model = head.next;

        int i = 0;
        while (model != head){
            nameModels[i] = model.name;
            model = model.next;
            i++;
        }

        return nameModels;
    }

    public double getPriceModel(String name){
        Model model = head.next;
        double price = Double.NaN;

        while (model != head){
            if (model.name.equals(name)){
                price = model.price;
                break;
            }
        }
        return price;
    }

    //  метод для модификации значения цены модели по её названию,
    public void setPriceModel(String name, double price){
        Model model = head.next;
        while (model != head){
            if (model.name.equals(name)){
                model.price = price;
                break;
            }
        }
    }

    //  метод для получения массива цен моделей
    public double[] getPriceModels(){
        double[] priceModels = new double[getCountModel()];
        Model model = head.next;

        int i = 0;
        while (model != head){
            priceModels[i] = model.price;
            model = model.next;
            i++;
        }

        return priceModels;
    }

    public void addModel(String name, double price){
        Model model = new Model(name, price);
        appendModel(model);
    }

    public void delModel(String name){
        int index = findModel(name);
        popModel(index);
    }

    public int findModel(String name, double price){
        Model model = head.next;
        int index = -1;
        int i = 0;
        while (model != head){
            if (model.name.equals(name) && model.price == price){
                index = i;
                break;
            }
            model = model.next;
            i++;
        }
        return index;
    }

    public int findModel(String name){
        Model model = head.next;
        int index = -1;
        int i = 0;
        while (model != head){
            if (model.name.equals(name)){
                index = i;
                break;
            }
            model = model.next;
            i++;
        }
        return index;
    }


    public int getCountModel(){
        int count = 0;
        Model models = head.next;

        while(models != head){
            models = models.next;
            count ++;
        }

        return count;
    }

    public Motorbike(String brand, int countBrand){
        this.brand = brand;
        for (int i = 0; i < countBrand; i++){
            String name = "Moto " + (i + 1);
            double price = Math.random() * 1000000;

            Model model = new Model(name, price);
            appendModel(model);
        }
    }

    public void appendModel(Model model){
        model.next = head.next;
        model.prev = head;
        head.next = model;

        if (head.prev == head){
            head.prev = model;
        }
    }

    public void popModel(int index){
        Model model = head.next;
        int i = 0;

        while (model != head){
            if (index == i){
                Model nextModel = model.next;
                Model prevModel = model.prev;
                nextModel.prev = prevModel;
                prevModel.next = nextModel;
                break;
            }
            model = model.next;
            i++;
        }

//        return model;
    }

    private class Model{
        String name = null;
        double price = Double.NaN;
        Model prev = null;
        Model next = null;

        public Model(){}

        public Model(String name, double price){
            this.name = name;
            this.price = price;
        }
    }



}
