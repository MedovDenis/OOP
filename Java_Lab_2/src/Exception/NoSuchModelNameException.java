package Exception;

public class NoSuchModelNameException extends Exception {
    public String nameModel;

    public NoSuchModelNameException(String name) {
        super ("Модель с именем \"" + name + "\" не существует");
        nameModel = name;
    }
}
