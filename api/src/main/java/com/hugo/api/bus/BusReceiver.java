package com.hugo.api.bus;

/**
 * Created by hq on 2015/11/16.
 */
public class BusReceiver {

  Bus bus ;
  public BusReceiver(Bus bus) {
    this.bus = bus;
    this.bus.register(this);
  }

  public void post(Object obj) {
    bus.post(obj);
  }
}
