package Transport;

import Interface.Transport;
import Exception.*;

import java.util.HashMap;

public class Scooter implements Transport {
    private static final long serialVersionUID = 1;
    private String brand;
    private HashMap<String, Double> models;
    private final String type = "Scooter";

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
        if (!(models.containsKey(name))) throw new NoSuchModelNameException(name);
        if (models.containsKey(nameNew)) throw new DuplicateModelNameException(name);

        double price = models.get(name);
        models.remove(name);

        models.put(nameNew, price);
    }

    public String[] getNameModels(){
        String[] names = new String[models.size()];

        int i = 0;
        for( String name : models.keySet()){
            names[i] = name;
            i ++;
        }

        return names;
    }

    public double getPriceModel(String name) throws NoSuchModelNameException{
        if (!(models.containsKey(name))) throw new NoSuchModelNameException(name);

        return models.get(name);
    }

    public void setPriceModel(String name, double price) throws NoSuchModelNameException{
        if (!(models.containsKey(name))) throw new NoSuchModelNameException(name);
        if (price < 0) throw new ModelPriceOutOfBoundsException();

        models.replace(name, price);
    }

    public double[] getPriceModels(){
        double[] prices = new double[models.size()];

        int i = 0;
        for( Double price : models.values()){
            prices[i] = price;
            i ++;
        }

        return prices;
    }

    public void addModel(String name, double price) throws DuplicateModelNameException {
        if (models.containsKey(name)) throw new DuplicateModelNameException(name);
        if (price < 0) throw new ModelPriceOutOfBoundsException();

        models.put(name, price);
    }

    public void delModel(String name, double price) throws NoSuchModelNameException{
        if (!(models.containsKey(name))) throw new NoSuchModelNameException(name);
        if (price < 0) throw new ModelPriceOutOfBoundsException();

        models.remove(name);
    }

    public int getCountModel(){
        return models.size();
    }

    public Scooter(String brand, int count){
        this.brand = brand;
        models = new HashMap<String, Double>();

        for(int i = 0; i < count; i++){
            String name = "Scooter" + (i + 1);
            double price = 100000 * (i + 1);
            models.put(name, price);
        }
    }

    public Scooter(String brand){
        this.brand = brand;
        models = new HashMap<String, Double>();
    }

    @Override
    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(type);
        stringBuffer.append('\n');
        stringBuffer.append(brand);

        String[] names = models.keySet().toArray(new String[0]);
        Double[] prices = models.values().toArray(new Double[0]);
        for(int i = 0; i < models.size(); i ++){
            stringBuffer.append('\n');
            stringBuffer.append(names[i]);
            stringBuffer.append('\n');
            stringBuffer.append(prices[i]);
        }
        return stringBuffer.toString();
    }
}
