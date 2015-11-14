package com.hugo.api.entites;

/**
 * Created by hq on 2015/11/13.
 *
 *
 */
public class Request implements Comparable<Request> {

  public static final String METHOD_POST = "POST";
  public static final String METHOD_GET = "GET";
  public String requestPath; //请求路径
  public String method ;
  public String tag = "default";
  private String uniqueId = tag+requestPath; //每次的唯一标识

  public Request(String requestPath, String tag) {
    this.requestPath = requestPath;
    this.tag = tag;
  }

  public String getUniqueId() {
    return uniqueId;
  }

  public int timeOut;

  @Override public int compareTo(Request another) {

    return another.requestPath.hashCode() - this.requestPath.hashCode();
  }
}
