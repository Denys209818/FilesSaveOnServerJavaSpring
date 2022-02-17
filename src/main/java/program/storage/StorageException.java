package program.storage;

//Помилка, яка виникатиме при некоректній роботі з файлами.
public class StorageException extends RuntimeException{
    //  Перший конструктор приймає повідомлення та передає його
    //  батьківському елементу. А батьківський елемент виникає,
    //  коли в середовищі запуску проекту виникає помилка
    public StorageException(String message) {
        super(message);
    }
    //  Другий конструктор приймає повідомлення і помилку та передає їх
    //  у батьківський елемент. А батьківський елемент виникає,
    //  коли в середовищі запуску проекту виникає помилка
    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
