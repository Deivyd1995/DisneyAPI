package alkemy.AlkemyChallenge.persistance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreDto {

    private Long id;
    private String name;
    private String image;

    public GenreDto() {
    }
}
