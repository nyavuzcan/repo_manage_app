package com.example.nk.Exception;
public class FileStorageException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private String msg;

  public FileStorageException(String msg) {
    super(msg);
  }
  public FileStorageException(String msg,Throwable cause) {
    super(msg,cause);
  }

  public String getMsg() {
    return msg;
  }
}
