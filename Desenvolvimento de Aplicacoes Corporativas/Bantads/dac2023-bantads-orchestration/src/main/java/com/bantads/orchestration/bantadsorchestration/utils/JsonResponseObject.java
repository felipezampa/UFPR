package com.bantads.orchestration.bantadsorchestration.utils;

public class JsonResponseObject {
  private Boolean success;
  private String message;
  private Object data;

  public JsonResponseObject(Boolean success, String message, Object data) {
    this.success = success;
    this.message = message;
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
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