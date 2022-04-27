package toyproject.annonymouschat.config;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.Connection;

@Slf4j
class DBConnectionUtilTest {

    @Test
    @DisplayName("getConnection성공케이스")
    void getConnection_성공케이스() {
        Connection connection = DBConnectionUtil.getConnection();
        Assertions.assertThat(connection).isNotNull();
        log.info("커넥션 드라이버 : {}", connection.getClass());
    }

}