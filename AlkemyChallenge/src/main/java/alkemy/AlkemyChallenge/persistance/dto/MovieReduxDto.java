package alkemy.AlkemyChallenge.persistance.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class MovieReduxDto {

    private Long id;
    private String image;
    private String title;
    private LocalDate creationDate;

    public MovieReduxDto() {
    }
}
