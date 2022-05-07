package alkemy.AlkemyChallenge.persistance.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;

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
public class CharacterDisney {

    @Id
    @Column(name = "id_character", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String name;
    private Integer age;
    private double weight;
    private String history;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "characters", cascade = CascadeType.MERGE)
    @JsonIgnoreProperties(value = {"characters"})
    private Set<Movie> movies = new HashSet<>();

}
