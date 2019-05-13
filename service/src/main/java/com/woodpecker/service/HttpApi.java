package com.woodpecker.service;

import com.sword.autotest.framework.annotation.http.Download;
import com.sword.autotest.framework.annotation.http.HttpRequest;
import com.sword.autotest.framework.annotation.http.Param;
import com.sword.autotest.framework.annotation.http.UploadFile;
import com.sword.autotest.framework.annotation.http.UploadName;
import com.sword.autotest.framework.annotation.http.Url;
import com.xujinjian.HttpClient.HttpResponse;
import com.xujinjian.HttpClient.RequestType;
import java.io.File;
import java.util.Map;

/**
 * 接口描述:〈Http请求工具类〉
 *
 * @author: jinjianxu
 * @since: 1.0
 */
public interface HttpApi {

  /**
   * 方法功能描述: 发送GET请求(使用同一个HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.GET)
  HttpResponse get(@Url String url);

  /**
   * 方法功能描述: 发送GET请求(使用同一个HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.GET)
  HttpResponse get(@Url String url, @Param String params);

  /**
   * 方法功能描述: 发送GET请求(使用同一个HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.GET)
  HttpResponse get(@Url String url, @Param Map<String, String> params);


  /**
   * 方法功能描述: 发送POST请求(使用同一个HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.POST)
  HttpResponse post(@Url String url);


  /**
   * 方法功能描述: 发送POST请求(使用同一个HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.POST)
  HttpResponse post(@Url String url, @Param String params);


  /**
   * 方法功能描述: 发送POST请求(使用同一个HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.POST)
  HttpResponse post(@Url String url, @Param Map<String, String> params);

  /**
   * 方法功能描述: 发送download请求(使用同一个HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.GET)
  HttpResponse download(@Url String url, @Download String filePath);


  /**
   * 方法功能描述: 发送download请求(使用同一个HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.GET)
  HttpResponse download(@Url String url, @Param String params, @Download String filePath);


  /**
   * 方法功能描述: 发送download请求(使用同一个HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.GET)
  HttpResponse download(@Url String url, @Param Map<String, String> params,
      @Download String filePath);

  /**
   * 方法功能描述: 发送upload请求(使用同一个HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.POST)
  HttpResponse upload(@Url String url, @UploadName String[] names, @UploadFile String[] filePaths);


  /**
   * 方法功能描述: 发送upload请求(使用同一个HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.POST)
  HttpResponse upload(@Url String url, @UploadName String[] names, @UploadFile File[] files);


  /**
   * 方法功能描述: 发送upload请求(使用同一个HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.POST)
  HttpResponse upload(@Url String url, @UploadName String[] names, @UploadFile String[] filePaths,
      @Param
          Map<String, String> params);


  /**
   * 方法功能描述: 发送upload请求(使用同一个HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.POST)
  HttpResponse upload(@Url String url, @UploadName String[] names, @UploadFile File[] files,
      @Param Map<String,
          String> params);


  /**
   * 方法功能描述: 发送GET请求(不带HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.GET, isUsePreviousSession = false)
  HttpResponse get2(@Url String url);


  /**
   * 方法功能描述: 发送GET请求(不带HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.GET, isUsePreviousSession = false)
  HttpResponse get2(@Url String url, @Param String params);


  /**
   * 方法功能描述: 发送GET请求(不带HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.GET, isUsePreviousSession = false)
  HttpResponse get3(@Url String url, @Param Map<String, String> params);


  /**
   * 方法功能描述: 发送POST请求(不带HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.POST, isUsePreviousSession = false)
  HttpResponse post2(@Url String url);


  /**
   * 方法功能描述: 发送POST请求(不带HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.POST, isUsePreviousSession = false)
  HttpResponse post2(@Url String url, @Param String params);


  /**
   * 方法功能描述: 发送POST请求(不带HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.POST, isUsePreviousSession = false)
  HttpResponse post2(@Url String url, @Param Map<String, String> params);


  /**
   * 方法功能描述: 发送download请求(不带HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.GET, isUsePreviousSession = false)
  HttpResponse download2(@Url String url, @Download String filePath);


  /**
   * 方法功能描述: 发送download请求(不带HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.GET, isUsePreviousSession = false)
  HttpResponse download2(@Url String url, @Param String params, @Download String filePath);


  /**
   * 方法功能描述: 发送download请求(不带HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.GET, isUsePreviousSession = false)
  HttpResponse download2(@Url String url, @Param Map<String, String> params,
      @Download String filePath);


  /**
   * 方法功能描述: 发送upload请求(不带HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.POST, isUsePreviousSession = false)
  HttpResponse upload2(@Url String url, @UploadName String[] names, @UploadFile String[] filePaths);


  /**
   * 方法功能描述: 发送upload请求(不带HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.POST, isUsePreviousSession = false)
  HttpResponse upload2(@Url String url, @UploadName String[] names, @UploadFile File[] files);


  /**
   * 方法功能描述: 发送upload请求(不带HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.POST, isUsePreviousSession = false)
  HttpResponse upload2(@Url String url, @UploadName String[] names, @UploadFile String[] filePaths,
      @Param
          Map<String, String> params);


  /**
   * 方法功能描述: 发送upload请求(不带HttpClientContext)
   *
   * @return HttpResponse
   */
  @HttpRequest(type = RequestType.POST, isUsePreviousSession = false)
  HttpResponse upload2(@Url String url, @UploadName String[] names, @UploadFile File[] files,
      @Param Map<String,
          String> params);


}
