package toyproject.annonymouschat.replychat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import toyproject.annonymouschat.config.controller.controller.ControllerWithMap;
import toyproject.annonymouschat.config.controller.ModelView;
import toyproject.annonymouschat.config.controller.ReturnType;
import toyproject.annonymouschat.replychat.dto.RepliesByChatIdDto;
import toyproject.annonymouschat.replychat.dto.RepliesByChatIdResponseDto;
import toyproject.annonymouschat.replychat.service.ReplyChatService;

import java.util.List;
import java.util.Map;

public class RepliesByChatIdServlet implements ControllerWithMap {
    private ReplyChatService replyChatService = new ReplyChatService();

    @Override
    @ReturnType(type = ReturnType.ReturnTypes.JSON)
    public ModelView process(Map<String, Object> requestParameters) {
        RepliesByChatIdDto dto = new RepliesByChatIdDto();
        dto.setChatId(Long.valueOf((String) requestParameters.get("chatId")));

        List<RepliesByChatIdResponseDto> findChats = replyChatService.findAllByChatId(dto);
        ModelView modelView = new ModelView();
        modelView.getModel().put("response", findChats);

        return modelView;
    }
}
