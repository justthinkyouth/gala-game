package com.cnbexpress.weixin.common.api;

import com.cnbexpress.weixin.common.exception.WxErrorException;

/**
 * WxErrorException处理器
 */
public interface WxErrorExceptionHandler {

  public void handle(WxErrorException e);

}
