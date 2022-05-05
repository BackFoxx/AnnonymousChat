package toyproject.annonymouschat.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class MyChatPostBoxResponseDto {
    private Long id;
    private String content;
    private Timestamp createDate;
}
