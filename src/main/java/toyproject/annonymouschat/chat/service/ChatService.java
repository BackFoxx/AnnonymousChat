package toyproject.annonymouschat.chat.service;

import toyproject.annonymouschat.chat.dto.ChatDeleteDto;
import toyproject.annonymouschat.chat.dto.ChatSaveDto;
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
    public List<Chat> findAll() {
        return repository.findAll();
    }
    public Chat getRandom() {
        return repository.getRandom();
    }

    public Chat findbyId(Long id) {
        return repository.findById(id);
    }
}
