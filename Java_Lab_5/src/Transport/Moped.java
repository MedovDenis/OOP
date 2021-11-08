package Transport;

import Interface.Transport;

import java.io.Serializable;
import java.util.LinkedList;

public class Moped implements Transport {
    private static final long serialVersionUID = 1;
    private String brand;
    private LinkedList<Model> models;
    private final String type = "Moped";

    public String getType(){
        return type;
    }

    public String getBrand(){
        return brand;
    }

    public void setBrand(String brand){

    }

    public void setNameModel(String name, String nameNew)throws NoSuchModelNameException, DuplicateModelNameException{

    }

    public String[] getNameModels(){

    }

    public double getPriceModel(String name) throws NoSuchModelNameException{

    }

    public void setPriceModel(String name, double price) throws NoSuchModelNameException{

    }

    public double[] getPriceModels(){

    }

    public void addModel(String name, double price) throws DuplicateModelNameException{

    }

    public void delModel(String name, double price) throws NoSuchModelNameException{

    }

    public int getCountModel(){

    }

    public Moped(String brand, int count){

    }

    class Model implements Serializable {

    }
}
