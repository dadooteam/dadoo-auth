package im.dadoo.auth.data.po;

import java.io.Serializable;

public class UserPO implements Serializable {
  
  private static final long serialVersionUID = 4009076183216352951L;
  
  private long              id;
  private String            email;
  private String            nickname;
  private String            password;
  private long              signupUTC;
  private long              signinUTC;
  private int               deleted;
  
  public UserPO() {}
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("UserPO [id=");
    builder.append(id);
    builder.append(", email=");
    builder.append(email);
    builder.append(", nickname=");
    builder.append(nickname);
    builder.append(", password=");
    builder.append(password);
    builder.append(", signupUTC=");
    builder.append(signupUTC);
    builder.append(", signinUTC=");
    builder.append(signinUTC);
    builder.append(", deleted=");
    builder.append(deleted);
    builder.append("]");
    return builder.toString();
  }
  
  public long getId() {
    return id;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getNickname() {
    return nickname;
  }
  
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public long getSignupUTC() {
    return signupUTC;
  }
  
  public void setSignupUTC(long signupUTC) {
    this.signupUTC = signupUTC;
  }
  
  public long getSigninUTC() {
    return signinUTC;
  }
  
  public void setSigninUTC(long signinUTC) {
    this.signinUTC = signinUTC;
  }
  
  public int getDeleted() {
    return deleted;
  }
  
  public void setDeleted(int deleted) {
    this.deleted = deleted;
  }
  
}
