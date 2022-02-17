package program.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import program.entities.AuthorEntity;
//  Репозиторій для автора
@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {
}
