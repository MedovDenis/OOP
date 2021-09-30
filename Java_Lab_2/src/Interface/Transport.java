package Interface;
import Exception.*;

import java.util.Arrays;

public interface Transport {
    String getBrand();

    void setBrand(String brand);

    String[] getNameModels();

    double getPriceModel(String name) throws NoSuchModelNameException;

    void setPriceModel(String name, double price) throws NoSuchModelNameException;

    double[] getPriceModels();

    void addModel(String name, double price) throws DuplicateModelNameException;

    void delModel(String name) throws NoSuchModelNameException;

    int getCountModel();

}

