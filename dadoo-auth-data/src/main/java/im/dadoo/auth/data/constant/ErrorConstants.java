package im.dadoo.auth.data.constant;

import im.dadoo.auth.data.dto.ErrorDTO;

public final class ErrorConstants {
  
  private ErrorConstants() {}
  
  public static final int CODE_SUCCESS = 1;
  
  public static final int CODE_QUERY_NULL = 90;
  
  public static final int CODE_NICKNAME_HAS_BEEN_USED = 100;
  public static final int CODE_EMAIL_HAS_BEEN_USED = 101;
  
  public static final int CODE_INSERT_ERROR = 110;
  public static final int CODE_DB_ERROR = 111;
  
  public static final int CODE_PASSWORD_INCORRECT = 120;
  
  public static final ErrorDTO ERROR_DTO_SUCCESS = new ErrorDTO(CODE_SUCCESS, "");
  
}
