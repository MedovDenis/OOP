package Interface;
import Exception.*;

import java.io.Serializable;
import java.util.Arrays;

public interface Transport extends Serializable, Cloneable {

    String getType();

    String getBrand();

    void setBrand(String brand);

    void setNameModel(String name, String nameNew)throws NoSuchModelNameException, DuplicateModelNameException;

    String[] getNameModels();

    double getPriceModel(String name) throws NoSuchModelNameException;

    void setPriceModel(String name, double price) throws NoSuchModelNameException;

    double[] getPriceModels();

    void addModel(String name, double price) throws DuplicateModelNameException;

    void delModel(String name, double price) throws NoSuchModelNameException;

    int getCountModel();

    String toString();

    boolean equals(Object obj);

    int hashCode();

    Object clone();
}

