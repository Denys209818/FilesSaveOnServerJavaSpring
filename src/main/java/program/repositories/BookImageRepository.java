package program.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import program.entities.BookImageEntity;
//  Репозиторій для фотографій книжок
@Repository
public interface BookImageRepository extends JpaRepository<BookImageEntity, Integer> {
}
