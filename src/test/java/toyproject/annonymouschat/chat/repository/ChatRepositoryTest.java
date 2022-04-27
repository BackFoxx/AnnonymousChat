package toyproject.annonymouschat.chat.repository;

import org.junit.jupiter.api.Test;
import toyproject.annonymouschat.chat.model.Chat;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

class ChatRepositoryTest {
    ChatRepository repository = new ChatRepository();

    @Test
    void chatRepository_전체프로세스() {
        //저장
        String content = "무뭄";
        Chat savedChat = repository.save(content);
        Long savedId = savedChat.getId();

        //조회
        Chat findChat = repository.findById(savedId);
        assertThat(findChat.getId()).isEqualTo(savedId);
        assertThat(findChat.getContent()).isEqualTo(content);

        //삭제
        repository.delete(savedId);
        assertThatThrownBy(() -> repository.findById(savedId))
                .isInstanceOf(NoSuchElementException.class);
    }
}