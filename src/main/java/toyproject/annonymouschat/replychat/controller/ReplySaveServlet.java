package toyproject.annonymouschat.replychat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.User.model.User;
import toyproject.annonymouschat.config.controller.controller.ControllerWithMap;
import toyproject.annonymouschat.config.controller.ModelView;
import toyproject.annonymouschat.config.controller.ReturnType;
import toyproject.annonymouschat.replychat.dto.ReplyChatSaveDto;
import toyproject.annonymouschat.replychat.dto.ReplyChatSaveDeleteResponseDto;
import toyproject.annonymouschat.replychat.service.ReplyChatService;

import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.util.Map;

@Slf4j
//@WebServlet(name = "reply", urlPatterns = "/v/reply/save")
public class ReplySaveServlet implements ControllerWithMap {
    private ReplyChatService replyChatService = new ReplyChatService();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @ReturnType(type = ReturnType.ReturnTypes.JSON)
    public ModelView process(Map<String, Object> requestParameters) {

        try {
            ReplyChatSaveDto dto = objectMapper.readValue(((ServletInputStream) requestParameters.get("requestBody")), ReplyChatSaveDto.class);
            Long userId = ((User) requestParameters.get("user")).getId();
            dto.setUserId(userId);

            replyChatService.saveReply(dto);

            log.info("reply 저장 완료");
            ReplyChatSaveDeleteResponseDto responseDto = new ReplyChatSaveDeleteResponseDto(true, "저장 완료되었습니다", "/v/chat/postbox");

            ModelView modelView = new ModelView();
            modelView.getModel().put("response", responseDto);
            return modelView;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
