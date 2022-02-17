package program.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import program.storage.ImageStorage;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {
    private final ImageStorage storage;

    //  Метод, який повертає відовідь з фотографією. На це вказує аргумент HttpHeaders.CONTENT_DISPOSITION,
    //  який вказує на те, що відповідь може використовуватися як сторінка або контент на сторінці.
    //  Метод статичного класу URLEncoder.encode приймає назву файлу і конвертує її у формат UTF8.
    //  Сама віповідь має тип контенту IMAGE_JPEG (MediaType). Заголовок filename вказує назву файлу яка
    //  буде виведена при скачуванні фотографії. Метод body містить тіло відповіді (ресурс)
    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getImage(@PathVariable (value = "filename")String filename) throws UnsupportedEncodingException {
        Resource resource = storage.loadAsResource(filename);
        String fileFullName = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\""+ fileFullName +"\"")
                .body(resource);
    }
}
