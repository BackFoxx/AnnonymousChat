package toyproject.annonymouschat.chat.service;

import toyproject.annonymouschat.chat.dto.ChatDeleteDto;
import toyproject.annonymouschat.chat.dto.ChatSaveDto;
import toyproject.annonymouschat.chat.dto.MyChatPostBoxResponseDto;
import toyproject.annonymouschat.chat.model.Chat;
import toyproject.annonymouschat.chat.repository.ChatRepository;

import java.util.List;

public class ChatService {
    ChatRepository repository = new ChatRepository();

    public Chat save(ChatSaveDto chatSaveDto) {
        return repository.save(chatSaveDto);
    }
    public void delete(ChatDeleteDto chatDeleteDto) {
        repository.delete(chatDeleteDto.getId());
    }
    public List<MyChatPostBoxResponseDto> findAllByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }
    public Chat getRandom(Long userId) {
        return repository.getRandom(userId);
    }

    public Chat findbyChatId(Long id) {
        return repository.findByChatId(id);
    }
}
