package com.cnbexpress.weixin.bean;

import com.cnbexpress.weixin.common.api.WxConsts;
import com.cnbexpress.weixin.common.util.xml.XStreamCDataConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("xml")
public class WxMpXmlOutTextMessage extends WxMpXmlOutMessage {
	private static final long serialVersionUID = -8077259064656584432L;
	
	@XStreamAlias("Content")
	@XStreamConverter(value = XStreamCDataConverter.class)
	private String content;

	public WxMpXmlOutTextMessage() {
		this.msgType = WxConsts.XML_MSG_TEXT;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
