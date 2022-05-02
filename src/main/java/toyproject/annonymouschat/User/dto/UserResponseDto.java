package toyproject.annonymouschat.User.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserResponseDto {
    private boolean ok;
    private String message;
    private String redirect;
}
