package Transport;

import Interface.Transport;
import Exception.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Quadbike implements Transport {
    private static final long serialVersionUID = 1;
    private String brand;
    private ArrayList<Model> models;
    private final String type = "Quadbike";

    public String getType(){
        return type;
    }

    public String getBrand(){
        return brand;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public void setNameModel(String name, String nameNew)throws NoSuchModelNameException, DuplicateModelNameException{
        int index = findModel(name);
        if (index == -1) throw new NoSuchModelNameException(name);
        if (findModel(nameNew) != -1) throw new DuplicateModelNameException(name);

        double price = models.get(index).price;
        models.set(index, new Model(nameNew, price));
    }

    public String[] getNameModels(){
        String[] names = new String[models.size()];
        int i = 0;
        for (Model model : models) {
            names[i] = model.name;
            i++;
        }
        return names;
    }

    public double getPriceModel(String name) throws NoSuchModelNameException{
        int index = findModel(name);
        if (index == -1) throw new NoSuchModelNameException(name);

        return models.get(index).price;
    }

    public void setPriceModel(String name, double price) throws NoSuchModelNameException{
        if (price < 0) throw new ModelPriceOutOfBoundsException();
        int index = findModel(name);
        if (index == -1) throw new NoSuchModelNameException(name);

        models.set(index, new Model(name, price));
    }

    public double[] getPriceModels(){
        double[] prices = new double[models.size()];
        int i = 0;
        for (Model model : models) {
            prices[i] = model.price;
            i++;
        }
        return prices;
    }

    public void addModel(String name, double price) throws DuplicateModelNameException{
        if (price < 0) throw new ModelPriceOutOfBoundsException();
        if ( findModel(name) != -1 ) throw new DuplicateModelNameException(name);

        models.add(new Model(name, price));
    }

    public void delModel(String name, double price) throws NoSuchModelNameException{
        int index = findModel(name);
        if (index == -1) throw new NoSuchModelNameException(name);

        models.remove(index);
    }

    private int findModel(String name){
        int i = 0;
        for (Model model : models) {
            if (model.name.equals(name)) return i;
            i ++;
        }
        return -1;
    }

    public int getCountModel(){
        return models.size();
    }

    public Quadbike(String brand, int count){
        this.brand = brand;
        models = new ArrayList<Model>();

        for(int i = 0; i < count; i++){
            String name = "Scooter" + (i + 1);
            double price = 100000 * (i + 1);
            models.add(new Model(name, price));
        }
    }

    class Model implements Serializable {
        String name;
        double price;

        public Model(String name, double price){
            this.name = name;
            this.price = price;
        }
    }
}
