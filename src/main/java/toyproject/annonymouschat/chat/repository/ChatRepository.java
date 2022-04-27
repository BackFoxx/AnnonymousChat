package toyproject.annonymouschat.chat.repository;

import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.chat.model.Chat;
import toyproject.annonymouschat.config.DBConnectionUtil;

import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
public class ChatRepository {
    // 게시글 저장
    public Chat save(String content) {

        String sql = "insert into chat(content) values(?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnectionUtil.getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Statement.RETURN_GENERATED_KEYS -> 생성된 컬럼의 아이디를 반환하는 파라미터
            pstmt.setString(1, content);
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                Long id = rs.getLong("id");
                log.info("Generated id = {}", id);
                return findById(id);
            } else {
                throw new NoSuchElementException("저장된 아이디를 찾을 수 없음");
            }

        } catch (SQLException e) {
            log.error("쿼리 실행 중 오류", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection(rs, conn, pstmt);
        }
    }

    //아이디를 이용한 단건조회
    public Chat findById(Long id) {
        String sql = "select * from chat where id=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnectionUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                Chat chat = new Chat(rs.getLong("id"),
                        rs.getString("content"),
                        rs.getTimestamp("createdate"));

                return chat;
            } else {
                throw new NoSuchElementException("해당 아이디로 게시글을 찾을 수 없음");
            }
        } catch (SQLException e) {
            log.error("쿼리 실행 중 오류", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection(rs, conn, pstmt);
        }
    }

    // 아이디를 이용한 삭제
    public void delete(Long id) {
        String sql = "delete from chat where id=?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnectionUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            log.error("쿼리 실행 중 오류", e);
            throw new RuntimeException(e);
        } finally {
            closeConnection(null, conn, pstmt);
        }
    }

    // 커넥션 종료
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
