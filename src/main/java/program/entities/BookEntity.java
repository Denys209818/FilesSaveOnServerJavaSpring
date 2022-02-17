package program.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="tbl_book_entity")
public class BookEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int Id;
    @Column(length = 200, nullable = false)
    private String Title;
    @Column(length = 200, nullable = false)
    private String Description;
    //  Звязок з фотографіями книжки
    @OneToMany(mappedBy = "book")
    private List<BookImageEntity> Images;
    //Звязок з ідентифікатором автора
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private AuthorEntity author;
}
