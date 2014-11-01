package im.dadoo.auth.biz.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import im.dadoo.auth.data.po.UserPO;

@Component
public class UserDAO extends BaseDAO<UserPO> {
  
  private static final String ALL_FIELDS = "id, email, nickname, password, signup_utc, signin_utc, deleted";
  
  private static final String SQL_INSERT = 
      "INSERT INTO t_user(email, nickname, password, signup_utc, signin_utc, deleted) "
      + "VALUES(:email, :nickname, :password, :signup_utc, :signin_utc, :deleted)";
  
  private static final String SQL_UPDATE_BASEINFO_BY_ID = 
      "UPDATE t_user SET email=:email, nickname=:nickname, password=:password "
      + "WHERE id=:id";
  
  private static final String SQL_UPDATE_SIGNIN_UTC_BY_ID = 
      "UPDATE t_user SET signin_utc=:signin_utc WHERE id=:id";
  
  private static final String SQL_REMOVE_BY_ID = 
      "UPDATE t_user SET deleted = 1 WHERE id=:id";
  
  private static final String SQL_DELETE_BY_ID = 
      "DELETE FROM t_user WHERE id=:id";
  
  private static final String SQL_FIND_BY_ID = 
      "SELECT " + ALL_FIELDS +
      " FROM t_user WHERE id=:id LIMIT 1";
  
  private static final String SQL_FIND_BY_NICKNAME = 
      "SELECT " + ALL_FIELDS + 
      " FROM t_user WHERE nickname=:nickname LIMIT 1";
  
  private static final String SQL_FIND_BY_EMAIL = 
      "SELECT " + ALL_FIELDS + 
      " FROM t_user WHERE email=:email LIMIT 1";
  
  private static final String SQL_LIST = 
      "SELECT " + ALL_FIELDS + 
      " FROM t_user ORDER BY id DESC";
  
  private static final String SQL_PAGE = 
      "SELECT " + ALL_FIELDS + 
      " FROM t_user ORDER BY id DESC LIMIT :offset, :pagesize";
  
  private static final String SIZE_SQL = 
      "SELECT count(*) AS size FROM t_user";
  
  @Resource
  private RowMapper<UserPO> userRowMapper;
  
  public UserDAO() {
    super(UserPO.class);
  }
  
  @Override
  public UserPO insert(UserPO userPO) throws DataAccessException, NullPointerException {
    Preconditions.checkNotNull(userPO, "userPO can not be null");
    KeyHolder holder = new GeneratedKeyHolder();
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("email", userPO.getEmail());
    sps.addValue("nickname", userPO.getNickname());
    sps.addValue("password", userPO.getPassword());
    sps.addValue("signup_utc", userPO.getSignupUTC());
    sps.addValue("signin_utc", userPO.getSigninUTC());
    sps.addValue("deleted", userPO.getDeleted());
    this.jdbcTemplate.update(SQL_INSERT, sps, holder);
    userPO.setId(holder.getKey().intValue());
    return userPO;
  }
  
  public void updateBaseInfoById(UserPO userPO) throws DataAccessException, NullPointerException {
    Preconditions.checkNotNull(userPO, "userPO can not be null");
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", userPO.getId());
    sps.addValue("email", userPO.getEmail());
    sps.addValue("nickname", userPO.getNickname());
    sps.addValue("password", userPO.getPassword());
    this.jdbcTemplate.update(SQL_UPDATE_BASEINFO_BY_ID, sps);
  }
  
  public void updateSigninUTCById(long id, long signinUTC) throws DataAccessException {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    sps.addValue("signin_utc", signinUTC);
    this.jdbcTemplate.update(SQL_UPDATE_SIGNIN_UTC_BY_ID, sps);
  }
  
  @Override
  public void deleteById(long id) throws DataAccessException {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    this.jdbcTemplate.update(SQL_DELETE_BY_ID, sps);
  }
  
  @Override
  public void removeById(long id) throws DataAccessException {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    this.jdbcTemplate.update(SQL_REMOVE_BY_ID, sps);
  }
  
  @Override
  public UserPO findById(long id) throws DataAccessException {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("id", id);
    List<UserPO> userPOs = this.jdbcTemplate.query(SQL_FIND_BY_ID, sps, this.userRowMapper);
    if (userPOs != null && !userPOs.isEmpty()) {
      return userPOs.get(0);
    } else {
      return null;
    }
  }
  
  public UserPO findByNickname(String nickname) throws DataAccessException {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("nickname", nickname);
    List<UserPO> userPOs = this.jdbcTemplate.query(SQL_FIND_BY_NICKNAME, sps, this.userRowMapper);
    if (userPOs != null && !userPOs.isEmpty()) {
      return userPOs.get(0);
    } else {
      return null;
    }
  }
  
  public UserPO findByEmail(String email) throws DataAccessException {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("email", email);
    List<UserPO> userPOs = this.jdbcTemplate.query(SQL_FIND_BY_EMAIL, sps, this.userRowMapper);
    if (userPOs != null && !userPOs.isEmpty()) {
      return userPOs.get(0);
    } else {
      return null;
    }
  }
  
  @Override
  public List<UserPO> list() throws DataAccessException {
    List<UserPO> userPOs = this.jdbcTemplate.query(SQL_LIST, this.userRowMapper);
    return userPOs;
  }
  
  @Override
  public List<UserPO> page(int pagecount, int pagesize) throws DataAccessException  {
    MapSqlParameterSource sps = new MapSqlParameterSource();
    sps.addValue("offset", pagecount * pagesize);
    sps.addValue("pagesize", pagesize);
    List<UserPO> userPOs = this.jdbcTemplate.query(SQL_PAGE, sps, this.userRowMapper);
    return userPOs;
  }
  
  @Override
  public int size() throws DataAccessException {
    return this.jdbcTemplate.queryForObject(SIZE_SQL, (SqlParameterSource)null, this.sizeRowMapper);
  }
}
