package alkemy.AlkemyChallenge.persistance.dto;

import alkemy.AlkemyChallenge.persistance.entity.Genre;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class MovieDto {

    private Long id;
    @Schema(example = "Aladdin", required = true)
    @NotBlank(message = "Title can't be blank")
    private String title;
    @Schema(example = "https://server.com/aladdin_image.jpg", required = true)
    @NotBlank(message = "Image can't be blank")
    private String image;
    @Schema(example = "5", required = true)
    @NotNull(message = "Rating can't be null")
    @Min(value = 1, message = "Rating must be greater or equal to 1")
    @Max(value = 5, message = "Rating must be less or equal to 5")
    private int qualification;
    @NotNull(message = "Date can't be null")
    private LocalDate creationDate;
    private Genre genre;
    private Set<CharacterReduxDto> characters;

    public MovieDto() {
    }
}
