public class DuplicateModelNameException extends Exception {
    public String nameModel;

    public DuplicateModelNameException(String name) {
        super ("Модель с именем \"" + name + "\" уже существует");
        nameModel = name;
    }
}
