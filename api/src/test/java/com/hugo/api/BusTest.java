package com.hugo.api;

import com.hugo.api.bus.Bus;
import com.hugo.api.bus.BusBean;
import java.util.Map;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by hq on 2015/11/15.
 */
public class BusTest {
  @Test
  public void register() {

    Bus bus = new Bus();
    BusBean bean = new BusBean();
    bus.register(bean);
    Map handlerByType = bus.handlerByType;
    Map registerWithType = bus.registerWithType;

    bus.unregister(bean);
    Map handlerByType1 = bus.handlerByType;
    Map registerWithType1 = bus.registerWithType;
    Assert.assertEquals(0,handlerByType1.size());

  }
}
