package toyproject.annonymouschat.replychat.controller;

import toyproject.annonymouschat.config.controller.ControllerAutoJson;
import toyproject.annonymouschat.config.controller.RequestBody;
import toyproject.annonymouschat.config.controller.ReturnType;
import toyproject.annonymouschat.replychat.dto.ReplyChatSaveDeleteResponseDto;
import toyproject.annonymouschat.replychat.dto.ReplyDeleteDto;
import toyproject.annonymouschat.replychat.service.ReplyChatService;

public class ReplyDeleteServlet implements ControllerAutoJson<ReplyDeleteDto> {
    private ReplyChatService replyChatService = new ReplyChatService();

    @Override
    @ReturnType(type = ReturnType.ReturnTypes.JSON)
    public Object process(@RequestBody ReplyDeleteDto requestBody) {
        replyChatService.deleteReply(requestBody);
        return new ReplyChatSaveDeleteResponseDto(true, "삭제 완료되었습니다", "/v/chat/myreply");
    }
}
