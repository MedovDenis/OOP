import java.util.Arrays;

public interface Transport {
    String getBrand();

    void setBrand(String brand);

    String[] getNameModels();

    double getPriceModel(String name) throws NoSuchModelNameException;

    public void setPriceModel(String name, double price) throws NoSuchModelNameException;

    public double[] getPriceModels();

    public void addModel(String name, double price) throws DuplicateModelNameException;

    public void delModel(String name) throws NoSuchModelNameException;

//    public int findModel(String name, double price);
//
//    public int findModel(String name);

    public int getCountModel();
}

