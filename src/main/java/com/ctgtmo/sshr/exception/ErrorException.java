package com.ctgtmo.sshr.exception;

/**
 * @Title: ErrorException.java   
 * @Company: 北京易才博普奥管理顾问有限公司
 * @Package: com.ctgtmo.sshr.exception   
 * @Description: 异常处理
 * @author: 王共亮     
 * @date: 2020年10月22日 上午10:23:38
 */
public class ErrorException extends Exception {

  private static final long serialVersionUID = 1L;

  public ErrorException() {
  }

  public ErrorException(String message) {
    super(message);
  }

  public ErrorException(String message, Throwable cause) {
    super(message, cause);
  }

  public ErrorException(Throwable cause) {
    super(cause);
  }

}
