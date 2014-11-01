package im.dadoo.auth.data.dto;

import java.io.Serializable;

public class ErrorDTO implements Serializable {

  private static final long serialVersionUID = 4954230977722514874L;
  
  private int    code;
  private String message;
  
  public ErrorDTO(int code, String message) {
    this.code = code;
    this.message = message;
  }
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ErrorDTO [code=");
    builder.append(code);
    builder.append(", message=");
    builder.append(message);
    builder.append("]");
    return builder.toString();
  }
  
  public int getCode() {
    return code;
  }
  
  public void setCode(int code) {
    this.code = code;
  }
  
  public String getMessage() {
    return message;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
}
