package toyproject.annonymouschat.replychat.service;

import toyproject.annonymouschat.replychat.dto.ReplyChatSaveDto;
import toyproject.annonymouschat.replychat.repository.ReplyChatRepository;

public class ReplyChatService {
    private ReplyChatRepository repository = new ReplyChatRepository();

    public void saveReply(ReplyChatSaveDto dto) {
        repository.saveReply(dto);
    }
}
