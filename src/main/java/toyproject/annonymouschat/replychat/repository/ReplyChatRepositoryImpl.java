package toyproject.annonymouschat.replychat.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;
import toyproject.annonymouschat.config.DatabaseException;
import toyproject.annonymouschat.replychat.dto.*;
import toyproject.annonymouschat.config.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ReplyChatRepositoryImpl implements ReplyChatRepository {
    @Override
    public void saveReply(ReplyChatSaveDto dto) {
        String sql = "insert into replychat (content, chatId, user_id) values (?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnectionUtil.getConnection();
            log.info("connection = {}", conn);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getContent());
            pstmt.setLong(2, dto.getChatId());
            pstmt.setLong(3, dto.getUserId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            closeConnection(null, conn, pstmt);
        }
    }

    @Override
    public List<RepliesByChatIdResponseDto> findAllByChatIdDto(RepliesByChatIdDto dto) {
        String sql = "select * from replychat where chatid = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnectionUtil.getConnection();
            log.info("connection = {}", conn);
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, dto.getChatId());

            rs = pstmt.executeQuery();

            List<RepliesByChatIdResponseDto> result = new ArrayList<>();
            while (rs.next()) {
                RepliesByChatIdResponseDto responseDto = new RepliesByChatIdResponseDto();
                responseDto.setContent(rs.getString("content"));
                responseDto.setCreateDate(rs.getTimestamp("createdate"));
                result.add(responseDto);
            }

            return result;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            closeConnection(rs, conn, pstmt);
        }
    }

    @Override
    public List<RepliesByUserIdResponseDto> findAllByUserIdDto(RepliesByUserIdDto dto) {
        String sql = "select C.CREATEDATE CHAT_CREATEDATE, C.CONTENT CHAT_CONTENT, RC.ID REPLY_ID, RC.CREATEDATE REPLY_CREATEDATE, RC.CONTENT REPLY_CONTENT from REPLYCHAT RC inner join CHAT C on C.ID = RC.CHATID where RC.USER_ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnectionUtil.getConnection();
            log.info("connection = {}", conn);
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, dto.getUserId());

            rs = pstmt.executeQuery();

            List<RepliesByUserIdResponseDto> result = new ArrayList<>();
            while (rs.next()) {
                RepliesByUserIdResponseDto responseDto = new RepliesByUserIdResponseDto();
                responseDto.setChatContent(rs.getString("CHAT_CONTENT"));
                responseDto.setChatCreateDate(rs.getTimestamp("CHAT_CREATEDATE"));

                responseDto.setReplyId(rs.getLong("REPLY_ID"));
                responseDto.setReplyContent(rs.getString("REPLY_CONTENT"));
                responseDto.setReplyCreateDate(rs.getTimestamp("REPLY_CREATEDATE"));

                result.add(responseDto);
            }

            return result;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            closeConnection(rs, conn, pstmt);
        }
    }

    @Override
    public void deleteReply(ReplyDeleteDto dto) {
        String sql = "delete from replychat where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnectionUtil.getConnection();
            log.info("connection = {}", conn);
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, dto.getReplyId());

            pstmt.executeUpdate();
            log.info("삭제 완료되었습니다. 삭제한 replyId = {}", dto.getReplyId());
        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            closeConnection(null, conn, pstmt);
        }
    }

    private void closeConnection(ResultSet rs, Connection conn, PreparedStatement pstmt) {
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(pstmt);
        JdbcUtils.closeConnection(conn);
    }
}
