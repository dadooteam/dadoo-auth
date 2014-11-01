package im.dadoo.auth.data.dto;

import im.dadoo.auth.data.po.UserPO;

import java.io.Serializable;

public class SignDTO implements Serializable {

  private static final long serialVersionUID = -3133298500966414555L;
  
  private UserPO   userPO;
  private ErrorDTO errorDTO;
  
  public SignDTO() {
    this(null, null);
  }
  
  public SignDTO(UserPO userPO, ErrorDTO errorDTO) {
    this.userPO = userPO;
    this.errorDTO = errorDTO;
  }
  
  public SignDTO(UserPO userPO, int errorCode, String errorMessage) {
    this(userPO, new ErrorDTO(errorCode, errorMessage)); 
  }
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("SignDTO [userPO=");
    builder.append(userPO);
    builder.append(", errorDTO=");
    builder.append(errorDTO);
    builder.append("]");
    return builder.toString();
  }

  public UserPO getUserPO() {
    return userPO;
  }
  
  public void setUserPO(UserPO userPO) {
    this.userPO = userPO;
  }
  
  public ErrorDTO getErrorDTO() {
    return errorDTO;
  }
  
  public void setErrorDTO(ErrorDTO errorDTO) {
    this.errorDTO = errorDTO;
  }
  
}
