package toyproject.annonymouschat.User.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;
import toyproject.annonymouschat.User.dto.UserRegistrationDto;
import toyproject.annonymouschat.User.model.User;
import toyproject.annonymouschat.config.DBConnectionUtil;
import toyproject.annonymouschat.config.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

@Slf4j
public class UserRepositoryImpl implements UserRepository {
    // 아이디를 이용한 삭제
    @Override
    public String registration(UserRegistrationDto dto) {
        String sql = "insert into user (useremail, password) values (?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnectionUtil.getConnection();
            log.info("connection = {}", conn);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getUserEmail());
            pstmt.setString(2, dto.getPassword());

            pstmt.executeUpdate();
            log.info("회원가입 완료되었습니다.");

            return dto.getUserEmail();
        } catch (SQLException e) {
            log.error("쿼리 실행 중 오류", e);
            throw new DatabaseException(e);
        } finally {
            closeConnection(null, conn, pstmt);
        }
    }

    @Override
    public User findByUserEmail(String userEmail) {
        String sql = "select * from user where useremail=?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnectionUtil.getConnection();
            log.info("connection = {}", conn);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userEmail);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(rs.getLong("id"), rs.getString("useremail"), rs.getString("password"));
            }

            throw new NoSuchElementException("해당하는 회원정보가 없습니다.");
        } catch (SQLException e) {
            log.error("쿼리 실행 중 오류", e);
            throw new DatabaseException(e);
        } finally {
            closeConnection(rs, conn, pstmt);
        }
    }

    // 커넥션 종료
    private void closeConnection(ResultSet rs, Connection conn, PreparedStatement pstmt) {
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(pstmt);
        JdbcUtils.closeConnection(conn);
    }
}
