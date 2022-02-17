package program.mapstructs;

import org.mapstruct.Mapper;
import program.dto.BookAddDto;
import program.dto.BookDto;
import program.entities.BookEntity;

@Mapper(componentModel = "spring")
public interface BookMapStruct {
    //  Конвертує модель для додавання книжок у сущность книжок
    BookEntity convertToBookEntity(BookAddDto model);
    //  Конвертує сущность книжок у модель книжки
    BookDto convertToBookDto(BookEntity model);
}
