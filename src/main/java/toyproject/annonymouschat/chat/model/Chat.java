package toyproject.annonymouschat.chat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    private Long id;
    private String content;
    private Timestamp createDate;
}
