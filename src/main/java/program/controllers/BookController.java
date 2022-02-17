package program.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import program.dto.BookAddDto;
import program.dto.BookDto;
import program.entities.BookEntity;
import program.entities.BookImageEntity;
import program.mapstructs.BookMapStruct;
import program.repositories.AuthorRepository;
import program.repositories.BookImageRepository;
import program.repositories.BookRepository;
import program.storage.ImageStorage;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {
    //  Сервіс для збереження фотографій
    private final ImageStorage storage;
    //  Репозиторій для книжок
    private final BookRepository bookRepository;
    //  Репозиторій для авторів
    private final AuthorRepository authorRepository;
    //  Маппер для книжок
    private final BookMapStruct bookMapStruct;
    //  Репозиторій для фотографій книжок
    private final BookImageRepository bookImageRepository;

    @PostMapping("/add")
    //  Метод, який приймає модель для додавання книжок.
    //  Даний метод створює книжку, задає назву для неї, зберігає фотографії на сервері
    public int AddBook(@RequestBody BookAddDto model) {
        BookEntity entity = bookMapStruct.convertToBookEntity(model);
        entity.setAuthor(authorRepository.getById(model.getAuthorId()));
        bookRepository.save(entity);
        for(String base64file: model.getImageBase64()) {
            String fileName = storage.saveImage(base64file);
            BookImageEntity bookImageEntity = new BookImageEntity();
            bookImageEntity.setImageName(fileName);
            bookImageEntity.setBook(entity);
            bookImageRepository.save(bookImageEntity);
        }

        return entity.getId();
    }

    //  Метод повертає колекцію книжок з БД
    @GetMapping("/get")
    public List<BookDto> getBooks()
    {
        return bookRepository.findAll().stream()
                .map(book -> bookMapStruct.convertToBookDto(book))
                .collect(Collectors.toList());
    }
}
