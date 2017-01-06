package com.cnbexpress.weixin.bean.result;

import com.cnbexpress.weixin.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * <pre>
 * 微信企业号发送消息返回结果
 * 
 * </pre>
 * 
 * @author tom.tao
 *
 */
public class WxEMassSendResult implements Serializable {
	private static final long serialVersionUID = -8262170869067560402L;
	
	private String errorCode;
	private String errorMsg;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public static WxEMassSendResult fromJson(String json) {
		return WxMpGsonBuilder.create().fromJson(json, WxEMassSendResult.class);
	}

	@Override
	public String toString() {
		return "WxEMassSendResult [errcode=" + errorCode + ", errmsg=" + errorMsg + "]";
	}

}
