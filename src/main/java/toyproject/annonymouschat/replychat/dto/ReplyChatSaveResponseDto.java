package toyproject.annonymouschat.replychat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class ReplyChatSaveResponseDto {
    private boolean ok;
    private String message;
    private String redirect;
}
