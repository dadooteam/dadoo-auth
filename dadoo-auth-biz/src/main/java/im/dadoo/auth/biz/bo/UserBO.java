package im.dadoo.auth.biz.bo;

import im.dadoo.auth.biz.dao.UserDAO;
import im.dadoo.auth.data.constant.UserConstants;
import im.dadoo.auth.data.po.UserPO;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

@Component
public class UserBO {
  
  @Resource
  private UserDAO userDAO;
  
  @Transactional
  public Optional<UserPO> insert(String nickname, String email, String password) throws DataAccessException {
    UserPO userPO = new UserPO();
    userPO.setNickname(nickname);
    userPO.setEmail(email);
    userPO.setPassword(password);
    userPO.setSignupUTC(System.currentTimeMillis());
    userPO.setSigninUTC(System.currentTimeMillis());
    userPO.setDeleted(UserConstants.USER_DELETED_NO);
    return Optional.fromNullable(this.userDAO.insert(userPO));
  }
  
  public boolean containNickname(String nickname) throws DataAccessException {
    if (this.userDAO.findByNickname(nickname) != null) {
      return true;
    } else {
      return false;
    }
  }
  
  public boolean containEmail(String email) throws DataAccessException {
    if (this.userDAO.findByEmail(email) != null) {
      return true;
    } else {
      return false;
    }
  }
  
}
