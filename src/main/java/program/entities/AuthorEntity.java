package program.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "tbl_author_entity")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(length = 200, nullable = false)
    private String Name;
    @Column(length = 1000, nullable = false)
    private String Description;
    //  Звязок з книжками даного автора
    @OneToMany(mappedBy = "author")
    private List<BookEntity> books;
}
