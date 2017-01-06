package com.cnbexpress.weixin.bean;

import com.cnbexpress.weixin.common.api.WxConsts;
import com.cnbexpress.weixin.common.util.xml.XStreamMediaIdConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("xml")
public class WxMpXmlOutImageMessage extends WxMpXmlOutMessage {
	private static final long serialVersionUID = -2497829875488167570L;
	
	@XStreamAlias("Image")
	@XStreamConverter(value = XStreamMediaIdConverter.class)
	private String mediaId;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public WxMpXmlOutImageMessage() {
		this.msgType = WxConsts.XML_MSG_IMAGE;
	}

}
