package toyproject.annonymouschat.chat.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import toyproject.annonymouschat.chat.dto.ChatSaveDto;
import toyproject.annonymouschat.chat.dto.MyChatPostBoxResponseDto;
import toyproject.annonymouschat.chat.model.Chat;
import toyproject.annonymouschat.config.DBConnectionUtil;
import toyproject.annonymouschat.config.DatabaseException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
public class ChatRepositoryImpl implements ChatRepository{

    private DataSource dataSource = DBConnectionUtil.getDataSource();
    private SQLExceptionTranslator exceptionTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);

    // 게시글 저장
    @Override
    public Long save(ChatSaveDto chatSaveDto) {

        String sql = "insert into chat(content, user_id) values(?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            log.info("connection = {}", conn);
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Statement.RETURN_GENERATED_KEYS -> 생성된 컬럼의 아이디를 반환하는 파라미터
            pstmt.setString(1, chatSaveDto.getContent());
            pstmt.setLong(2, chatSaveDto.getUserId());
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                Long id = rs.getLong("id");
                log.info("Generated id = {}", id);
                return id;
            } else {
                throw new NoSuchElementException("저장된 아이디를 찾을 수 없음");
            }

        } catch (SQLException e) {
            log.error("쿼리 실행 중 오류", e);
            throw exceptionTranslator.translate("chatRepository-save", sql, e);
        } finally {
            closeConnection(rs, conn, pstmt);
        }
    }

    @Override
    public List<MyChatPostBoxResponseDto> findAllByUserId(Long userId) {
        String sql = "select * from chat where user_id=?";

        List<MyChatPostBoxResponseDto> chats = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            log.info("connection = {}", conn);
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, userId);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String content = rs.getString("content");
                Timestamp createdate = rs.getTimestamp("createdate");

                MyChatPostBoxResponseDto dto = new MyChatPostBoxResponseDto(id, content, createdate);
                chats.add(dto);
            }
            return chats;
        } catch (SQLException e) {
            throw exceptionTranslator.translate("chatRepository_findAllByUserID", sql, e);
        } finally {
            closeConnection(rs, conn, pstmt);
        }
    }

    //Chat 아이디를 이용한 단건조회
    @Override
    public Chat findByChatId(Long id) {
        String sql = "select * from chat where id=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            log.info("connection = {}", conn);
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                Chat chat = new Chat(rs.getLong("id"),
                        rs.getString("content"),
                        rs.getTimestamp("createdate"),
                        rs.getLong("user_id"));

                return chat;
            } else {
                throw new NoSuchElementException("해당 아이디로 게시글을 찾을 수 없음");
            }
        } catch (SQLException e) {
            log.error("쿼리 실행 중 오류", e);
            throw exceptionTranslator.translate("chatRepository_findByChatID", sql, e);
        } finally {
            closeConnection(rs, conn, pstmt);
        }
    }

    @Override
    public Chat getRandom(Long userId) {
        String sql = "select * from chat where not user_id in (?) order by rand() limit 1";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            log.info("connection = {}", conn);
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, userId);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                Chat chat = new Chat(rs.getLong("id"),
                        rs.getString("content"),
                        rs.getTimestamp("createdate"),
                        rs.getLong("user_id"));

                return chat;
            } else {
                log.info("저장된 게시글이 없습니다.");
                return null;
            }
        } catch (SQLException e) {
            log.error("쿼리 실행 중 오류", e);
            throw exceptionTranslator.translate("chatRepository_getRandom", sql, e);
        } finally {
            closeConnection(rs, conn, pstmt);
        }
    }

    // 아이디를 이용한 삭제
    @Override
    public void delete(Long id) {
        String sql = "delete from chat where id=?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = dataSource.getConnection();
            log.info("connection = {}", conn);
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            pstmt.executeUpdate();
            log.info("정상적으로 삭제되었습니다.");

        } catch (SQLException e) {
            log.error("쿼리 실행 중 오류", e);
            throw exceptionTranslator.translate("chatRepository_delete", sql, e);
        } finally {
            closeConnection(null, conn, pstmt);
        }
    }

    // 커넥션 종료
    private void closeConnection(ResultSet rs, Connection conn, PreparedStatement pstmt) {
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(pstmt);
        JdbcUtils.closeConnection(conn);
    }
}
