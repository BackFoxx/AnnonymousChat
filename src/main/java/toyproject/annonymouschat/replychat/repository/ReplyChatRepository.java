package toyproject.annonymouschat.replychat.repository;

import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.replychat.dto.ReplyChatSaveDto;
import toyproject.annonymouschat.config.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class ReplyChatRepository {
    public void saveReply(ReplyChatSaveDto dto) {
        String sql = "insert into replychat (content, chatId) values (?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnectionUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getContent());
            pstmt.setLong(2, dto.getChatId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(null, conn, pstmt);
        }
    }

    private void closeConnection(ResultSet rs, Connection conn, PreparedStatement pstmt) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            log.error("ResultSet 닫는 중 오류", e);
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            log.error("preparedStatement 닫는 중 오류", e);
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            log.error("Connection 닫는 중 오류", e);
        }
    }
}
