package toyproject.annonymouschat.chat.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    private Long id;
    private String content;
    private Timestamp createDate;
}
