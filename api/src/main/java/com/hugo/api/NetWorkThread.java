package com.hugo.api;

import com.hugo.api.entites.Request;
import com.hugo.api.entites.Response;
import com.hugo.common.utils.IOUtils;
import com.hugo.common.utils.LogUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

/**
 * Created by hq on 2015/11/13.
 */
public class NetWorkThread extends Thread {

  private static final java.lang.String TAG = "NetWorkThread";

  private boolean isQuited = false;

  private final NetCallbackHelper netCallbackHelper;
  private BlockingQueue<Request> blockingQueue;

  private Set<Response> testSet = new HashSet<Response>();
  public NetWorkThread(BlockingQueue<Request> blockingQueue, NetCallbackHelper netCallbackHelper) {
    this.blockingQueue = blockingQueue;
    this.netCallbackHelper = netCallbackHelper;
  }

  public void quit() {
    this.isQuited = true;
    interrupt();
  }

  private void resetState() {

    this.isQuited = false;
  }

  @Override public void run() {
    super.run();
    LogUtils.d(TAG,Thread.currentThread().getName()+"启动线程。。。");
    while (true) {

      resetState();
      //循环获取request,有可能直接跳出循环，并返回
      Request request = null;
      while (true) {
        try {
          request = (Request) this.blockingQueue.take();
          break;
        } catch (InterruptedException var4) {
          if (this.isQuited) {
            return;
          }
        }
      }

      //开始网络请求
      HttpURLConnection httpURLConnection = null;
      Response response = null;
      try {
        httpURLConnection = doRequest(request);
        response = openReadStream(httpURLConnection);
      } catch (Exception e) {
        LogUtils.d(TAG, e.getMessage());
        if (response != null) {
          response.error = e;
        }
      } finally {
        if (httpURLConnection != null) {
          httpURLConnection.disconnect();
        }
        //处理最后的结果
        if (response != null) {
          response.setUniqueId(request.getUniqueId()); //传递唯一标识给它，让他找这个标识号
         // netCallbackHelper.postResponse(response);
          NetRequestQueue.getInstance().testMap.put(response.getUniqueId(), response);

        }
      }
    }
  }

  private Response openReadStream(HttpURLConnection httpURLConnection) {
    InputStream ips = null;
    ByteArrayOutputStream ops = null;
    Response response = new Response();
    try {
      ips = httpURLConnection.getInputStream();
      ops = new ByteArrayOutputStream();

      byte[] buf = new byte[1024 * 1024];
      int len = -1;
      while ((len = ips.read(buf)) > 0) {
        ops.write(buf, 0, len);
      }
      String contentStr = ops.toString("UTF-8"); //TODO 默认以utf-8 返回

      //判断数据是否为空
      response.setDataState("".equals(contentStr) ? 0:1);
      response.contentStr = contentStr;
    } catch (IOException e) {
      LogUtils.d(TAG,e.getMessage());
      if (response != null) {
        response.error = e;
      }
    } finally {
      IOUtils.close(ips, ops);
    }

    return response;
  }

  private HttpURLConnection doRequest(Request request) throws IOException {

    if (request == null) {
      throw new IllegalArgumentException("request is null");
    }
    String method = request.method;
    String requestPath = request.requestPath;

    URL url = new URL(requestPath); //TODO  注意要转成 utf-8的编码格式的地址
    HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
    httpConnection.setRequestMethod(method);
    httpConnection.setReadTimeout(request.timeOut);
    httpConnection.setConnectTimeout(request.timeOut);
    httpConnection.setDoInput(true);
    httpConnection.setDoOutput(true);
    httpConnection.setUseCaches(false);
    httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    httpConnection.setRequestProperty("Connection", "keep-alive");
    httpConnection.setRequestProperty("Response-Type", "json");
    httpConnection.setChunkedStreamingMode(0);

    return httpConnection;
  }
}
