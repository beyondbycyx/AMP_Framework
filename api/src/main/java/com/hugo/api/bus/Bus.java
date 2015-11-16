package com.hugo.api.bus;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by hq on 2015/11/15.
 *
 * 该bus 目前只能在main-thread 上执行，
 * 因为：
 * 1.设置它的默认ThreadRuler  是指定的main-thread
 * 2.它的集合都是非同步的
 */
public class Bus {

  private static final String TAG = "Bus";
  private ThreadRuler ruler ;

  //方法参数类型-具有相同参数类型的签名方法
  private final Map<Class<?>, Set<HandlerWithMethod>> handlerByType = new HashMap<>();

  //对象的类，所有的方法参数类型
  private final Map<Class<?>,Set<Class<?>>> registerWithType = new HashMap<Class<?>,Set<Class<?>>>();
  private final Set<Class<?>> registerSet = new HashSet<Class<?>>();

  public Bus() {
    this(ThreadRuler.MAIN_RULER);

  }

  public Bus(ThreadRuler ruler) {
    this.ruler = ruler;
  }
  /**
   * 将该对象的引用，和所有的InvokeRemoteArg 方法保存起来
   * 由外部大的集合开始找，为null则初始化，再往内部小的集合找，重复这样的操作
   * @param obj
   */
  public void register(Object obj) {
    if (obj == null) {
      throw new IllegalArgumentException("Object cannot be null");
    }

    //this.ruler.enforce(this);

    //曾经注册的先解除注册，在进行重新的注册。
    if (registerSet.contains(obj.getClass())) {
      unregister(obj);
    }

    boolean isPutinMap = false;
    //获取是否曾经注册过的obj
    Class<?> clazz = obj.getClass();
    Method[] declaredMethods = clazz.getDeclaredMethods();

    //将所有的方法以方法参数的类型分类
    for(Method method :declaredMethods){ //取出每一个方法
      //获取InvokeRemoteArg 注解的方法，
      if (method.isBridge())  //跳过没有用处的桥接方法。
        continue;

      if (method.isAnnotationPresent(InvokeRemoteArg.class) &&
          method.getParameterTypes().length == 1) { //存在该注释，且方法参数只有一个

        //取出方法参数的类型
        Class<?> type = method.getParameterTypes()[0];

        //保存 handlerByType
        Set<HandlerWithMethod> methodSet = handlerByType.get(type);
        if (methodSet == null) {
          methodSet = new HashSet<HandlerWithMethod>();
          handlerByType.put(type, methodSet); // put --- type , set
        }
        HandlerWithMethod handlerWithMethod = new HandlerWithMethod(obj, method);
        methodSet.add(handlerWithMethod);   // put --- (obj,method)

        //保存  registerWithType
        Set<Class<?>> methodTypes = registerWithType.get(obj.getClass());
        if (methodTypes == null) {
          methodTypes = new HashSet<Class<?>>();
          registerWithType.put(obj.getClass(), methodTypes);
        }
        methodTypes.add(type);

        //确保要值已经put进来了
        isPutinMap  = true;
      }
    }

    if (isPutinMap) {
      registerSet.add(obj.getClass()); //执行到这里代表有值put进去集合，才表示有注册成功。
    }

  }


  public void post(Object event){
    if (event == null) {
      throw new IllegalArgumentException("Object cannot be null");
    }

    //this.ruler.enforce(this);

    //找出handler
    Set<HandlerWithMethod> handlerWithMethods = handlerByType.get(event.getClass());
    if (handlerWithMethods == null) {
      return ;
    }
    for (HandlerWithMethod handlerWithMethod : handlerWithMethods) {

      Object handler = handlerWithMethod.handler;
      Method method = handlerWithMethod.method;

      try {
        //反射调用方法。
        method.setAccessible(true);
        method.invoke(handler, event);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }
  public void unregister(Object obj) {

    if (obj == null) {
      throw new IllegalArgumentException("Object cannot be null");
    }

    //this.ruler.enforce(this);

    //由里到外的删除
    Set<Class<?>> argTypeSet = registerWithType.get(obj.getClass());

    if (argTypeSet == null) {
      return ; // 没有注册过，直接删除掉
    }
    //删除里面的 handlerByType
    for(Class<?> argType : argTypeSet){

      Set<HandlerWithMethod> handlerWithMethods = handlerByType.get(argType);
      if (handlerWithMethods != null) {

        for (HandlerWithMethod handlerWithMethod : handlerWithMethods) {
            //取出每一个 (obj , method)中的obj.并判断 obj.class == obj.class
          if( handlerWithMethod.handler.getClass() == obj.getClass()){
            handlerWithMethods.remove(handlerWithMethod); // 删除它，以该obj对应的class.
          }
        }
      }
    }
    // 删除外面的 registerWithType
    registerWithType.remove(obj.getClass());

  }



  static class HandlerWithMethod{
    private Object handler ;
    private Method method ;
    public HandlerWithMethod(Object handler, Method method) {
      this.handler = handler;
      this.method = method;
    }
  }
}

