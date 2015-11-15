package com.hugo.api.entites;

/**
 * Created by hq on 2015/11/13.
 */
public class Response<T> {
  public static final int DataSate_Error = -1;
  public static final int DataSate_Empty = 0;
  public static final int DataSate_Exist = 1;
  public Exception error = new EmptyException();
  public String postOnThread = "";

  private int DataState = -1; //代表是否获取到了“存在”的数据

  public String getUniqueId() {
    return uniqueId;
  }

  public void setUniqueId(String uniqueId) {
    this.uniqueId = uniqueId;
  }

  private String uniqueId = "";
  public String contentStr = "";


  public void setDataState(int dataState) {
    DataState = dataState;
  }
  public int getDataState() {
    return DataState;
  }

  static class EmptyException extends Exception{
    public EmptyException(){
      super("EmptyException");
    }
  }
}
