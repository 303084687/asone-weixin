package com.ctgtmo.sshr.constant;

public class ApiResponse {
  public static final int FAIL = 0;

  public static final int SUCCESS = 1;

  public static final int OTHER = 2;

  int status;

  String message;

  Object data;

  public ApiResponse() {
  }

  public ApiResponse(int code, String msg, Object data) {
    this(code, msg);
    this.data = data;
  }

  public ApiResponse(int code, String msg) {
    switch (code) {
      case FAIL:
        setStatus(0);
        break;
      case SUCCESS:
        setStatus(1);
        break;
      case OTHER:
        setStatus(2);
        break;
    }

    this.message = msg;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

}
