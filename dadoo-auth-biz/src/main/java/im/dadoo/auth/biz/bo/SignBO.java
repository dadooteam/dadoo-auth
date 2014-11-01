package im.dadoo.auth.biz.bo;

import java.util.Date;

import im.dadoo.auth.biz.dao.UserDAO;
import im.dadoo.auth.data.constant.ErrorConstants;
import im.dadoo.auth.data.constant.UserConstants;
import im.dadoo.auth.data.dto.ErrorDTO;
import im.dadoo.auth.data.dto.SignDTO;
import im.dadoo.auth.data.po.UserPO;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Optional;

@Component
public class SignBO {
  
  private static final Logger logger = LoggerFactory.getLogger(SignBO.class);
  
  @Resource
  private UserDAO userDAO;
  
  @Transactional
  public Optional<SignDTO> signup(String nickname, String email, String password) {
    if (this.containNickname(nickname)) {
      String msg = String.format("nickname{%s} has been used", nickname);
      logger.info(msg);
      return Optional.of(new SignDTO(null, new ErrorDTO(ErrorConstants.CODE_NICKNAME_HAS_BEEN_USED, msg)));
    }
    if (this.containEmail(email)) {
      String msg = String.format("email{%s} has been used", email);
      logger.info(msg);
      return Optional.of(new SignDTO(null, new ErrorDTO(ErrorConstants.CODE_EMAIL_HAS_BEEN_USED, msg)));
    }
    UserPO userPO = new UserPO();
    userPO.setNickname(nickname);
    userPO.setEmail(email);
    userPO.setPassword(password);
    userPO.setSignupUTC(System.currentTimeMillis());
    userPO.setSigninUTC(System.currentTimeMillis());
    userPO.setDeleted(UserConstants.USER_DELETED_NO);
    try {
      return Optional.fromNullable(new SignDTO(this.userDAO.insert(userPO), ErrorConstants.ERROR_DTO_SUCCESS));
    } catch (DataAccessException e) {
      String msg = String.format("userPO{%s} insert error", userPO);
      logger.error(msg);
      logger.error(e.getMessage());
      return Optional.of(new SignDTO(null, new ErrorDTO(ErrorConstants.CODE_INSERT_ERROR, msg)));
    }
  }
  
  @Transactional
  public Optional<SignDTO> signin(String email, String password) {
    try {
      UserPO userPO = this.userDAO.findByEmail(email);
      if (userPO != null) {
        if (userPO.getPassword().equals(password)) {
          long signinUTC = System.currentTimeMillis();
          this.userDAO.updateSigninUTCById(userPO.getId(), signinUTC);
          logger.info(String.format("user{%s} sign in at %s", userPO.getId(), new Date(signinUTC)));
          return Optional.of(new SignDTO(this.userDAO.findById(userPO.getId()), ErrorConstants.ERROR_DTO_SUCCESS));
        } else {
          String msg = String.format("incorrect password{%s} for the email{%s}", password, email);
          logger.error(msg);
          return Optional.of(new SignDTO(null, new ErrorDTO(ErrorConstants.CODE_PASSWORD_INCORRECT, msg)));
        }
      } else {
        String msg = String.format("email{%s} is not existed", email);
        logger.info(msg);
        return Optional.of(new SignDTO(null, new ErrorDTO(ErrorConstants.CODE_QUERY_NULL, msg)));
      }
    } catch (DataAccessException e) {
      String msg = String.format("email{%s} has failed to sign in", email);
      logger.error(msg);
      logger.error(e.getMessage());
      return Optional.of(new SignDTO(null, new ErrorDTO(ErrorConstants.CODE_DB_ERROR, msg)));
    }
  }
  
  public boolean containNickname(String nickname) throws DataAccessException {
    try {
      if (this.userDAO.findByNickname(nickname) != null) {
        return true;
      } else {
        return false;
      }
    } catch (DataAccessException e) {
      logger.error(String.format("findByNickname error with nickname{%s}", nickname));
      logger.error(e.getMessage());
      return false;
    }
    
  }
  
  public boolean containEmail(String email) throws DataAccessException {
    try {
      if (this.userDAO.findByEmail(email) != null) {
        return true;
      } else {
        return false;
      }
    } catch (DataAccessException e) {
      logger.error(String.format("findByEmail error with email{%s}", email));
      logger.error(e.getMessage());
      return false;
    }
  }
}
