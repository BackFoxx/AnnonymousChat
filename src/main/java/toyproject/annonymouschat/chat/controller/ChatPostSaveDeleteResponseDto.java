package toyproject.annonymouschat.chat.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ChatPostSaveDeleteResponseDto {
    private boolean ok;
    private String message;
    private String redirect;
}
