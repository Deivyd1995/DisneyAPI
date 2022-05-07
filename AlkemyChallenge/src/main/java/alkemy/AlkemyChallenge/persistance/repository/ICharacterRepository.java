package alkemy.AlkemyChallenge.persistance.repository;


import alkemy.AlkemyChallenge.persistance.entity.CharacterDisney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICharacterRepository extends JpaRepository<CharacterDisney,Long> {

    @Query("SELECT c FROM CharacterDisney c WHERE c.name = ?1")
    List<CharacterDisney> findCharacterByName(String nombre);

    @Query("SELECT c FROM CharacterDisney c WHERE c.age = ?1")
    List<CharacterDisney> findCharacterByAge(Integer edad);

}
