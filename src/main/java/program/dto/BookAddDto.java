package program.dto;

import lombok.Data;

//  Модель для додавання книжок
@Data
public class BookAddDto {
    public String Title;
    public String Description;
    public String[] ImageBase64;
    public int AuthorId;
}
