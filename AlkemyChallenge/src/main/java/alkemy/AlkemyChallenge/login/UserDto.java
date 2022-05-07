package alkemy.AlkemyChallenge.login;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDto {

    @Schema(example = "usuario_disney@gmail.com", required = true)
    @NotBlank(message = "Email can't be empty")
    @Email(message = "Email must have a valid format")
    private String email;

    @Schema(example = "BestApiOfAllTimes", required = true)
    @NotBlank(message = "Password can't be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

}
