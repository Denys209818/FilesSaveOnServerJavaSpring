package program.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import program.dto.AuthorAddDto;
import program.dto.AuthorDto;
import program.entities.AuthorEntity;
import program.mapstructs.AuthorMapStruct;
import program.repositories.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorRepository authorRepository;
    private final AuthorMapStruct authorMapStruct;
    @PostMapping("/add")
    //  Метод, який створює автора у БД. Приймає модель для додавання автора
    public AuthorAddDto AddAuthor(@RequestBody AuthorAddDto model)
    {
        AuthorEntity entity = authorMapStruct.convertToAuthorEntity(model);
        authorRepository.save(entity);
        return model;
    }

    //  Метод, який конвертує сущность авторів у модель і повретає їх колекцію
    @GetMapping("/get")
    public List<AuthorDto> getAuthors()
    {
        return authorRepository.findAll().stream()
                .map(author -> authorMapStruct.convertToAuthorDto(author))
                .collect(Collectors.toList());
    }
}
