package Exception;

public class ModelPriceOutOfBoundsException extends RuntimeException{
    public ModelPriceOutOfBoundsException() {
        super ("Задание неверной цены модели");
    }
}
