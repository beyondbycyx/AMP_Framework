package com.hugo.api.entites;

/**
 * Created by hq on 2015/11/13.
 *
 *
 */
public class Request implements Comparable<Request> {

  public static final String METHOD_POST = "POST";
  public static final String METHOD_GET = "GET";
  public int timeOut = 5000;
  public String method = "GET" ;
  public String tag = "default";

  public String requestPath; //请求路径
  private String uniqueId = ""; //每次的唯一标识

  public Request(String requestPath, String tag) {
    this.requestPath = requestPath;
    this.tag = tag;
    this.uniqueId = requestPath+tag;
  }

  public String getUniqueId() {
    return uniqueId;
  }



  @Override public int compareTo(Request another) {

    return another.requestPath.hashCode() - this.requestPath.hashCode();
  }
}
