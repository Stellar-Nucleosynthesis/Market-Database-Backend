package tech.zlagoda.market_database_backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tech.zlagoda.market_database_backend.pojos.UserInfo;

@Repository
public class UserInfoRepository {
    @Autowired
    public UserInfoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final JdbcTemplate jdbc;

    public void addUserInfo(UserInfo userInfo) {
        String sql = "INSERT INTO User_Info (username, password, id_employee) VALUES (?, ?, ?);";
        jdbc.update(sql, userInfo.getUsername(), userInfo.getPassword(), userInfo.getEmployee().getIdEmployee());
    }

    public void deleteUserInfo(String username) {
        String sql = "DELETE FROM User_Info WHERE username = ?;";
        jdbc.update(sql, username);
    }

    public UserInfo getUserInfo(String username) {
        RowMapper<UserInfo> userInfoRowMapper = UserInfo.getRowMapper();
        String sql = "SELECT *" +
                    " FROM User_Info INNER JOIN Employee ON User_Info.id_employee = Employee.id_employee" +
                    " WHERE username = ?;";
        return jdbc.queryForObject(sql, userInfoRowMapper, username);
    }
}