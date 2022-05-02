package toyproject.annonymouschat.User.repository;

import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.User.dto.UserRegistrationDto;
import toyproject.annonymouschat.config.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class UserRepository {
    // 아이디를 이용한 삭제
    public String registration(UserRegistrationDto dto) {
        String sql = "insert into user (useremail, password) values (?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBConnectionUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getUserEmail());
            pstmt.setString(2, dto.getPassword());

            pstmt.executeUpdate();
            log.info("회원가입 완료되었습니다.");

            return dto.getUserEmail();
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
