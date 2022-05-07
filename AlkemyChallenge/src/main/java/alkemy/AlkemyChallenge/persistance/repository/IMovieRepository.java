package alkemy.AlkemyChallenge.persistance.repository;

import alkemy.AlkemyChallenge.persistance.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IMovieRepository extends JpaRepository<Movie,Long> {

    @Query("SELECT m FROM Movie m WHERE m.title = ?1")
    List<Movie> findMoviesByTitle(String titulo);

    @Query(value = "SELECT * FROM Movie  WHERE gender = ?1", nativeQuery = true)
    List<Movie> findMoviesByGender(Long genero_id);

    @Query(value ="SELECT * FROM Movie ORDER BY creation_date", nativeQuery = true)
    List<Movie> findMoviesByCreationDate();

    @Query(value ="SELECT * FROM Movie ORDER BY creation_date DESC", nativeQuery = true)
    List<Movie> findMoviesByCreationDateInOrderDescent();

}
