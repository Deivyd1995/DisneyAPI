package alkemy.AlkemyChallenge.persistance.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@ToString
/**me crea un contructor con todo menos con el id por que el se crea autoincremental en la bd*/
@AllArgsConstructor
/**me crea un contructor vacio*/
@NoArgsConstructor
public class Movie {

    @Id
    @Column(name = "id_movie",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String image;
    private int qualification;
    private LocalDate creationDate;
    @ManyToOne
    @JoinColumn(name = "gender", nullable = false)
    private Genre genre;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "characters_movies",
            joinColumns = @JoinColumn(name = "id_movie"),
            inverseJoinColumns = @JoinColumn(name = "id_character"))
    private Set<CharacterDisney> characters = new HashSet<>();


}
