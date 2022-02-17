package program.dto;

import lombok.Data;

//  Модель, яка представляє собою сущность автора
@Data
public class AuthorDto {
    private int Id;
    private String Name;
    private String Description;
}
