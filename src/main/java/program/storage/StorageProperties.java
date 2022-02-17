package program.storage;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Path;

// Валстивості проекта (додаткові)
@ConfigurationProperties("images")
@Data
public class StorageProperties {
    //  Кореневий шлях до папки з фотографіями
    private String locationPath = "uploads";
}
