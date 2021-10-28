package Transport;
import Interface.*;
import Exception.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Auto implements Transport{
    private static final long serialVersionUID = 1;
    private String brand;
    private Model[] models;
    private final String type = "Auto";

    public String getType() {return type;}

    public String getBrand() { return brand; }

    public void setBrand(String brand){ this.brand = brand; }

    public void setNameModel(String name, String nameNew)throws NoSuchModelNameException, DuplicateModelNameException{
        if(findModel(nameNew) != - 1){
            throw new DuplicateModelNameException(nameNew);
        }

        int index = findModel(name);
        if (index == -1){
            throw new NoSuchModelNameException(name);
        }
        else{
            models[index].setName(nameNew);
        }

    }

    public String[] getNameModels(){
        int len = models.length;
        String[] nameModels = new String[len];
        for (int i = 0; i < len; i++){
            nameModels[i] = models[i].getName();
        }
        return nameModels;
    }

    public double getPriceModel(String name) throws NoSuchModelNameException {
        int index = findModel(name);
        if(index != -1){
            return models[index].getPrice();
        }
        else{
            throw new NoSuchModelNameException(name);
        }
    }

    public void setPriceModel(String name, double price) throws NoSuchModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException();
        }
        int index = findModel(name);
        if(index != -1){
            models[index].setPrice(price);
        }
        else{
            throw new NoSuchModelNameException(name);
        }
    }

    public double[] getPriceModels(){
        int len = models.length;
        double[] priceModels = new double[len];
        for(int i = 0; i < len; i++){
            priceModels[i] = models[i].getPrice();
        }
        return priceModels;
    }

    public void addModel(String name, double price) throws DuplicateModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException();
        }
        if (findModel(name) == -1){
            models = Arrays.copyOf(models, models.length + 1 );
            Model newModel = new Model(name, price);
            models[models.length - 1] = newModel;
        }
        else{
            throw new DuplicateModelNameException(name);
        }
    }

    public void delModel(String name, double price) throws NoSuchModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException();
        }
        int index = findModel(name);
        if (findModel(name, price) != -1) {
            System.arraycopy(models, index + 1, models, index, models.length - index - 1);
            models = Arrays.copyOf(models, models.length - 1);
        }
        else{
            throw new NoSuchModelNameException(name);
        }
    }

    public int findModel(String name, double price){
        int len = getCountModel();
        int i = 0;
        while(i < len){
            if (models[i].getName().equals(name) && models[i].getPrice() == price){
                return i;
            }
            i++;
        }
        return -1;
    }

    public int findModel(String name){
        int len = models.length;
        int i = 0;
        while(i < len){
            if (models[i].getName().equals(name)){
                return i;
            }
            i++;
        }
        return -1;
    }

    public int getCountModel(){ return models.length; }

    public Auto(String brand, int countBrand){
        this.brand = brand;
        models = new Model[countBrand];

        for (int i = 0; i < countBrand; i++){
            String name = "Auto " + (i + 1);
            double price = 100000 * (i + 1);
            models[i] = new Model(name, price);
        }
    }

    @Override
    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(type);
        stringBuffer.append('\n');
        stringBuffer.append(brand);
        for(int i = 0; i < models.length; i ++){
            stringBuffer.append('\n');
            stringBuffer.append(models[i].toString());
        }
        return stringBuffer.toString();
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Auto)) return false;
        if( !(type.equals(((Auto) obj).getType()))) return false;
        if( !(brand.equals(((Auto) obj).getBrand()))) return false;
        return (Arrays.equals(models, ((Auto) obj).models));
    }

    @Override
    public int hashCode(){
        int hashCode = 0;
        hashCode = Objects.hash(type, brand);
        hashCode += Arrays.hashCode(models);
        return hashCode;
    }

    @Override
    public Object clone(){
        Auto result = null;
        try {
            result = (Auto) super.clone();
            result.models = models.clone();
            for(int i = 0; i < models.length; i++){
                result.models[i] = (Model) models[i].clone();
            }
        } catch (CloneNotSupportedException ex) {
            System.out.println(ex);
        }
        return result;
    }

    public Auto(String brand){
        this.brand = brand;
        models = new Model[0];
    }

    class Model implements Serializable, Cloneable{
        private String name = null;
        private double price = Double.NaN;

        public void setName(String name){ this.name = name;}

        public String getName(){
            return name;
        }

        public void setPrice(double price){
            this.price = price;
        }

        public double getPrice(){
            return price;
        }

        @Override
        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(name);
            stringBuffer.append('\n');
            stringBuffer.append(price);
            return stringBuffer.toString();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Model)) return false;
            Model model = (Model) obj;
            return Double.compare(model.price, price) == 0 && name.equals(model.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, price);
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return (Model) super.clone();
        }

        public Model(String name, double price){
            this.name = name;
            this.price = price;
        }
    }

}
