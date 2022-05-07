package alkemy.AlkemyChallenge.persistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
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
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    @OneToMany(mappedBy = "genre")
    @JsonIgnore
    private Set<Movie> movies;
}
