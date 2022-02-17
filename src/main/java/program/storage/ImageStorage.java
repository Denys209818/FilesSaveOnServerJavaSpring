package program.storage;

import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;
// Інтерфейс, який надає сигнатуру методів для конвертації
// base64 у фотографію та збереження її на сервері
public interface ImageStorage {
    //  Сигнатура метода, який при відсутності папки створює її
    void initDirectory();
    //  Сигнатура метода, який зберігає фотографію у папку. Приймає base64
    String saveImage(String filename);
    //  Сигнатура метода, який формує відносний шлях до фотографії
    Path getFilePath(String filename);
    //  Сигнатура метода, який повретає колекцію фотографій
    Stream<Path> loadAll();
    //  Сигнатура метода, який загружає фотографію як ресурс
    Resource loadAsResource(String filename);
}
