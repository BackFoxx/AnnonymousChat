package toyproject.annonymouschat.User.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRegistrationDto {
    private String userEmail;
    private String password;
}
