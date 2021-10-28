package Transport;
import Interface.*;
import Exception.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Motorbike implements Transport {
    private static final long serialVersionUID = 1;
    private String brand;
    private int size = 0;
    private Model head = new Model(null, Double.NaN);
    private final String type = "Motorbike";
    {
        head.prev = head;
        head.next = head;
    }

    public  String getType(){return  type;}

    public String getBrand() { return brand; }

    public void setBrand(String brand){ this.brand = brand; }

    public void setNameModel(String name, String nameNew)throws NoSuchModelNameException, DuplicateModelNameException{
        if(findModel(nameNew) != - 1){
            throw new DuplicateModelNameException(nameNew);
        }

        if (findModel(name) == -1){
            throw new NoSuchModelNameException(name);
        }
        else{
            Model model = head.next;
            while (model != head){
                if (model.name.equals(name)){
                    model.name = nameNew;
                    return;
                }
                model = model.next;
            }
        }
    }

    public String[] getNameModels(){
        String[] nameModels = new String[size];
        Model model = head.prev;

        int i = 0;
        while (model != head){
            nameModels[i] = model.name;
            model = model.prev;
            i++;
        }

        return nameModels;
    }

    public double getPriceModel(String name) throws NoSuchModelNameException {
        Model model = head.next;

        while (model != head){
            if (model.name.equals(name)){
                return model.price;
            }
            model = model.next;
        }
        throw new NoSuchModelNameException(name);
    }

    //  метод для модификации значения цены модели по её названию,
    public void setPriceModel(String name, double price) throws NoSuchModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException();
        }
        Model model = head.next;
        while (model != head){
            if (model.name.equals(name)){
                model.price = price;
                return;
            }
            model = model.next;
        }
        if(model == head){
            throw new NoSuchModelNameException(name);
        }
    }

    //  метод для получения массива цен моделей
    public double[] getPriceModels(){
        double[] priceModels = new double[size];
        Model model = head.prev;

        int i = 0;
        while (model != head){
            priceModels[i] = model.price;
            model = model.prev;
            i++;
        }

        return priceModels;
    }

    public void addModel(String name, double price) throws DuplicateModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException();
        }
        Model model = new Model(name, price);
        if(findModel(name) == -1){
            appendModel(model);

        }
        else {
            throw new DuplicateModelNameException(name);
        }
    }

    public void delModel(String name, double price) throws NoSuchModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException();
        }
        int index = findModel(name);
        if (findModel(name, price) != -1){
            popModel(index);
        }
        else{
            throw new NoSuchModelNameException(name);
        }

    }

    public int findModel(String name, double price){
        Model model = head.next;
        int i = 0;
        while (model != head){
            if (model.name.equals(name) && model.price == price){
                return i;
            }
            model = model.next;
            i++;
        }
        return -1;
    }

    public int findModel(String name){
        Model model = head.next;
        int i = 0;
        while (model != head){
            if (model.name.equals(name)){
                return i;
            }
            model = model.next;
            i++;
        }
        return -1;
    }

    public void appendModel(Model model){
        if (model != null) {
            model.next = head.next;
            model.prev = head;
            head.next.prev = model;
            head.next = model;
            size ++;
        }
    }

    public void popModel(int index){
        Model model = head.next;
        int i = 0;

        while (model != head){
            if (index == i){
                model.next.prev = model.prev;
                model.prev.next = model.next;
                size --;
                return;
            }
            model = model.next;
            i++;
        }
    }

    public int getCountModel(){ return size; }

    @Override
    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(type);
        stringBuffer.append('\n');
        stringBuffer.append(brand);

        String[] nameModels = getNameModels();
        double[] priceModels = getPriceModels();
        for(int i = 0; i < size; i++){
            stringBuffer.append('\n');
            stringBuffer.append(nameModels[i]);
            stringBuffer.append('\n');
            stringBuffer.append(priceModels[i]);
        }
        return stringBuffer.toString();
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Motorbike)) return false;
        if( !(type.equals(((Motorbike) obj).getType()))) return false;
        if( !(brand.equals(((Motorbike) obj).getBrand()))) return false;
        return Arrays.equals(this.getNameModels(), ((Motorbike) obj).getNameModels()) &&
                Arrays.equals(this.getPriceModels(), ((Motorbike) obj).getPriceModels());
    }

    @Override
    public int hashCode(){
        int hashCode = 0;
        hashCode = Objects.hash(type, brand);
        Model model = head.next;
        while (model != head){
            hashCode += model.hashCode();
            model = model.next;
        }
        return hashCode;
    }

    @Override
    public Object clone() {
        Motorbike result = null;
        try {
            result = (Motorbike) super.clone();
            result.head = new Model(null, Double.NaN);
            result.head.next = result.head;
            result.head.prev = result.head;
            result.size = 0;

            String[] nameModels = getNameModels();
            double[] priceModels = getPriceModels();
            for(int i = 0; i < size; i++){
                result.addModel(nameModels[i], priceModels[i]);
            }
        }
        catch (CloneNotSupportedException | DuplicateModelNameException ex) {
            System.out.println(ex);
        }
        return result;
    }

    public Motorbike(String brand){
        this.brand = brand;
    }

    public Motorbike(String brand, int countBrand){
        this.brand = brand;
        for (int i = 0; i < countBrand; i++){
            String name = "Moto " + (i + 1);
            double price = 100000 * (i + 1);

            Model model = new Model(name, price);
            appendModel(model);
        }
    }

    private class Model implements Serializable{
        String name = null;
        double price = Double.NaN;
        Model prev = null;
        Model next = null;

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
