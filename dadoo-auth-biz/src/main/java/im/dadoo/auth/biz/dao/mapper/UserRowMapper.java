package im.dadoo.auth.biz.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import im.dadoo.auth.data.po.UserPO;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserRowMapper implements RowMapper<UserPO> {
  
  @Override
  public UserPO mapRow(ResultSet rs, int rowNum) throws SQLException {
    UserPO userPO = new UserPO();
    userPO.setId(rs.getLong("id"));
    userPO.setNickname(rs.getString("nickname"));
    userPO.setEmail(rs.getString("email"));
    userPO.setPassword(rs.getString("password"));
    userPO.setSignupUTC(rs.getLong("signup_utc"));
    userPO.setSigninUTC(rs.getLong("signin_utc"));
    userPO.setDeleted(rs.getInt("deleted"));
    return userPO;
  }
  
}
