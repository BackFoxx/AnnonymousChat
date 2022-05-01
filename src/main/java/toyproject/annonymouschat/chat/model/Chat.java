package toyproject.annonymouschat.chat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy/MM/dd hh:mm")
    private Timestamp createDate;
}
