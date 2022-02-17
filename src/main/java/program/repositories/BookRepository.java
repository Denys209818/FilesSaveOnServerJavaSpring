package program.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import program.entities.BookEntity;
//  Репозиторій для книжок
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
}
