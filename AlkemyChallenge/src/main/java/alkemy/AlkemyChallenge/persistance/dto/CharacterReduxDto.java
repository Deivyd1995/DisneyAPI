package alkemy.AlkemyChallenge.persistance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterReduxDto {

    private Long id;
    private String name;
    private String image;

    public CharacterReduxDto() {
    }
}
