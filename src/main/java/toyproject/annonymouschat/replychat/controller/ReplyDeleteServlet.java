package toyproject.annonymouschat.replychat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import toyproject.annonymouschat.config.controller.ControllerWithMap;
import toyproject.annonymouschat.config.controller.ModelView;
import toyproject.annonymouschat.config.controller.ReturnType;
import toyproject.annonymouschat.replychat.dto.ReplyChatSaveDeleteResponseDto;
import toyproject.annonymouschat.replychat.dto.ReplyDeleteDto;
import toyproject.annonymouschat.replychat.service.ReplyChatService;

import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.util.Map;

//@WebServlet(name = "replyDelete", urlPatterns = "/v/reply/delete")
public class ReplyDeleteServlet implements ControllerWithMap {
    private ObjectMapper objectMapper = new ObjectMapper();
    private ReplyChatService replyChatService = new ReplyChatService();

    @Override
    @ReturnType(type = ReturnType.ReturnTypes.JSON)
    public ModelView process(Map<String, Object> requestParameters) {
        try {
            ReplyDeleteDto dto = objectMapper.readValue(((ServletInputStream) requestParameters.get("requestBody")), ReplyDeleteDto.class);
            replyChatService.deleteReply(dto);

            ReplyChatSaveDeleteResponseDto responseDto = new ReplyChatSaveDeleteResponseDto(true, "삭제 완료되었습니다", "/v/chat/myreply");
            ModelView modelView = new ModelView();
            modelView.getModel().put("response", responseDto);

            return modelView;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
