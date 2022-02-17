package program.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbl_book_image_entity")
@Data
public class BookImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(length = 200, nullable = false)
    private String ImageName;

    //  Звязок фотографії книжок до книжки
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;
}
