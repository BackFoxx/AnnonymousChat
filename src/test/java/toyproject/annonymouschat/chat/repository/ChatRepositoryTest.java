package toyproject.annonymouschat.chat.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import toyproject.annonymouschat.chat.dto.ChatSaveDto;
import toyproject.annonymouschat.chat.model.Chat;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@Slf4j
class ChatRepositoryTest {
    ChatRepository repository = new ChatRepository();
//
//    @Test
//    void chatRepository_전체프로세스() {
//        //저장
//        ChatSaveDto chatSaveDto = new ChatSaveDto();
//        chatSaveDto.setContent("무뭄");
//        Chat savedChat = repository.save(chatSaveDto);
//        Long savedId = savedChat.getId();
//
//        //조회
//        Chat findChat = repository.findByUserId(savedId);
//        assertThat(findChat.getId()).isEqualTo(savedId);
//        assertThat(findChat.getContent()).isEqualTo(chatSaveDto.getContent());
//
//        //삭제
//        repository.delete(savedId);
//        assertThatThrownBy(() -> repository.findByUserId(savedId))
//                .isInstanceOf(NoSuchElementException.class);
//    }
//
//    @Test
//    void chatRepository_전체조회() {
//        List<Chat> findChats = repository.findAllByUserId();
//        for (Chat findChat : findChats) {
//            Chat assertChat = repository.findByUserId(findChat.getId());
//            assertThat(findChat).isEqualTo(assertChat);
//        }
//    }
//
//    @Test
//    void chatRandom() {
//        Chat random = repository.getRandom();
//        Chat random2 = repository.getRandom();
//        assertThat(random.getId()).isNotEqualTo(random2.getId());
//    }
}