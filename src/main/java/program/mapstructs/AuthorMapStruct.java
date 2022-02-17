package program.mapstructs;

import org.mapstruct.Mapper;
import program.dto.AuthorAddDto;
import program.dto.AuthorDto;
import program.entities.AuthorEntity;

@Mapper(componentModel = "spring")
//  Мапер, який конвертує сущность автора у обєкт моделі і конвертує модель для додавання
//  автора у сущность автора
public interface AuthorMapStruct {
    AuthorDto convertToAuthorDto(AuthorEntity model);
    AuthorEntity convertToAuthorEntity(AuthorAddDto model);

}
