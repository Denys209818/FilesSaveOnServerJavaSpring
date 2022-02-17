package program.storage;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ImageStorageService implements ImageStorage {
    private final StorageProperties storage;
    private Path finalPath;

    @Override
    //  Метод, який формує шлях до папки на основі заданого у властивостях шляху
    //  Виконує перевірку, якщо папку не знайдено то вона створюється
    public void initDirectory() {
        try
        {
            Path directoryPath = Paths.get(storage.getLocationPath());
            finalPath = directoryPath;
            if(!Files.exists(directoryPath))
            {
                Files.createDirectory(directoryPath);
            }
        }
        catch (IOException ex)
        {
            throw new StorageException("Помилка створення папки!", ex);
        }
    }
    //  Метод, який приймає фотографію base64,
    //  у блоці try...catch перевіряє чи строка не пуста.
    //  Якщо строка пуста виникає помилка.
    //  Створюється відповідний масив, який містить 2 строки (відділяється інформація про фотографію та ам код фотографії).
    //  Генерується обєкт UUID, який повертає методом toString() своє значення. На основі йього методу формується назва
    //  фотографії.Потім декодером декодується фотографія у формат байтів. Та завдяки обєкту FileOutputStream створюється
    //  нова фотографія (обєкт приймає параметром шлях до файлу і має метод write, який може записати байтовий масив у
    //  фотографію). Повертаєтсья назва файлу
    @Override
    public String saveImage(String filename) {
        try
        {

        if(filename.isEmpty())
        {
            throw new StorageException("Строка пуста!");
        }
        String [] charArray = filename.split(",");
        UUID uuid = UUID.randomUUID();
        String randomFileName = uuid.toString() + ".jpg";
        byte[] bytes = new byte[0];
        Base64.Decoder decoder = Base64.getDecoder();
        bytes = decoder.decode(charArray[1]);

        new FileOutputStream(this.finalPath.resolve(randomFileName).toString()).write(bytes);
        return randomFileName;

        }
        catch(IOException ex)
        {
            throw new StorageException("Неможливо зберегти файл!", ex);
        }
    }

    //  Із строки формується шлях на основі шляху властивостей і повертається шлях до файлу
    //  у обєкті
    @Override
    public Path getFilePath(String filename) {
        Path file = Paths.get(filename);
        return file;
    }
    //  Метод, який проходить по папці з фотографіями (по 1 рівню).
    //  Відбирає фотографії у яких шлях не дорівнює папці та методом
    //  map формується колекція з відносних шляхів
    @Override
    public Stream<Path> loadAll() {
        try
        {
            return Files.walk(this.finalPath, 1)
                    .filter(path -> !path.equals(storage.getLocationPath()))
                    .map(path -> this.finalPath.relativize(path));
        }
        catch(IOException ex)
        {
            throw new StorageException("Помилка вибірки файлів!", ex);
        }
    }

    //  Метод, який приймає строку з назвлю файлу
    //  Методом getFilePath формується шлях типу Path (метод приймає параметром складений шлях
    //  методом Path.resolve).
    //  Виконується перевірка, якщо ресурс інсує чи він читабельний то він повертається.
    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path path = getFilePath(this.finalPath.resolve(filename).toString());
            Resource resource = new UrlResource(path.toUri());
            if(resource.exists() || resource.isReadable())
            {
                return resource;
            }else
            {
                throw new StorageException("Ресурс пустий");
            }
        }
        catch(Exception ex)
        {
            throw new StorageException("Помилка при повертанні ресурса!", ex);
        }
    }
}
