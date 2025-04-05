package tech.zlagoda.market_database_backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tech.zlagoda.market_database_backend.pojos.UserInfo;

import java.util.List;

@Repository
public class UserInfoRepository {
    @Autowired
    public UserInfoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final JdbcTemplate jdbc;

    public UserInfo getUserInfo(String username) {
        RowMapper<UserInfo> userInfoRowMapper = UserInfo.getRowMapper();
        String sql = "SELECT *" +
                    " FROM User_Info INNER JOIN Employee ON User_Info.id_employee = Employee.id_employee" +
                    " WHERE username = ?;";
        List<UserInfo> list = jdbc.query(sql, userInfoRowMapper, username);
        if(list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }
}