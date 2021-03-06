package alkemy.AlkemyChallenge.persistance.repository;

import alkemy.AlkemyChallenge.persistance.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IGenreRepository extends JpaRepository<Genre, Long> {
}
