package toyproject.annonymouschat.chat.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import toyproject.annonymouschat.chat.dto.ChatSaveDto;
import toyproject.annonymouschat.chat.model.Chat;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@Slf4j
class ChatRepositoryTest {
    ChatRepository repository = new ChatRepository();

    @Test
    void chatRepository_전체프로세스() {
        //저장
        ChatSaveDto chatSaveDto = new ChatSaveDto();
        chatSaveDto.setContent("무뭄");
        Chat savedChat = repository.save(chatSaveDto);
        Long savedId = savedChat.getId();

        //조회
        Chat findChat = repository.findById(savedId);
        assertThat(findChat.getId()).isEqualTo(savedId);
        assertThat(findChat.getContent()).isEqualTo(chatSaveDto.getContent());

        //삭제
        repository.delete(savedId);
        assertThatThrownBy(() -> repository.findById(savedId))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void chatRepository_전체조회() {
        List<Chat> findChats = repository.findAll();
        for (Chat findChat : findChats) {
            Chat assertChat = repository.findById(findChat.getId());
            assertThat(findChat).isEqualTo(assertChat);
        }
    }
}