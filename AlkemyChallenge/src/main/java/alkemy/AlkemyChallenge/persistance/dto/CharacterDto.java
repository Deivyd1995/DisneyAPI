package alkemy.AlkemyChallenge.persistance.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class CharacterDto {

    private Long id;
    @Schema(example = "https://server.com/mickey_image.jpg", required = true)
    @NotBlank(message = "ImageURL can't be blank")
    private String image;
    @Schema(example = "Mickey", required = true)
    @NotBlank(message = "Name can't be blank")
    private String name;
    @Schema(example = "2", required = true)
    @NotNull
    @Min(value = 0, message = "Age must be greater or equal to 0")
    private Integer age;
    @Schema(example = "22.5", required = true)
    @NotNull
    @Min(value = 0, message = "Weight must be greater or equal to 0")
    private double weight;
    @Schema(example = "Mickey history is an odd one", required = true)
    @NotBlank(message = "History can't be blank")
    private String history;
    private Set<MovieReduxDto> movies;

    public CharacterDto() {
    }
}
