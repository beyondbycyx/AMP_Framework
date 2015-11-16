package com.hugo.api.bus;

/**
 * Created by hq on 2015/11/15.
 */
public  class BusBean {


  public String str ;
  public Object obj ;

  public  Bus bus  ;
  public BusBean(Bus bus) {
    this.bus = bus;
    this.bus.register(this);
  }
  @InvokeRemoteArg
  public  void m2(String str){

    this.str = str;
    System.out.println("str = "+str);
  }
  @InvokeRemoteArg
  public  void m3(Object obj){

    this.obj = obj;
    System.out.println("obj = "+obj.hashCode());

  }
  public  void m1(){

  }

  public  void m3(){

  }
}

