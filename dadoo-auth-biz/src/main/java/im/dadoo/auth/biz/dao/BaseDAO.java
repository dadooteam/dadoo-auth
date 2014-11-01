package im.dadoo.auth.biz.dao;

import im.dadoo.auth.biz.dao.mapper.SizeRowMapper;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * 
 * @author shuwen.zsw
 *
 * @param <T>
 */
public class BaseDAO<T> {
  
  @Resource
  protected NamedParameterJdbcTemplate jdbcTemplate;
  
  @Resource
  protected SizeRowMapper              sizeRowMapper;
  
  protected Class<T>                   c;
  
  public BaseDAO(Class<T> c) {
    this.c = c;
  }
  
  public T insert(T obj) throws DataAccessException, NullPointerException {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public T update(T obj) throws DataAccessException, NullPointerException {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public void deleteById(long id) throws DataAccessException {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public void removeById(long id) throws DataAccessException {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public T findById(long id) throws DataAccessException {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public List<T> list() throws DataAccessException {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public List<T> page(int pagecount, int pagesize) throws DataAccessException {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  public int size() throws DataAccessException {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
