public class NoSuchModelNameException extends Exception {
    public String nameModel;

    public NoSuchModelNameException(String name) {
        super ("Модель с именем \"" + name + "\" уже не существует");
        nameModel = name;
    }
}
