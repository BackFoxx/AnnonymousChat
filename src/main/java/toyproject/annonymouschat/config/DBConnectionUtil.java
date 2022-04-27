package toyproject.annonymouschat.config;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static toyproject.annonymouschat.config.ConnectionConst.*;
/*
ConnectionConst의 정보를 바탕으로
커넥션을 반환합니다.

SQLException은 체크 예외이므로
RuntimeException으로 변환하여 던집니다.
*/
@Slf4j
public class DBConnectionUtil {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            log.error("exception", e);
            throw new RuntimeException(e);
        }
    }
}
