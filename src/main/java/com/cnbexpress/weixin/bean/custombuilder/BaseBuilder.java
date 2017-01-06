package com.cnbexpress.weixin.bean.custombuilder;

import com.cnbexpress.weixin.bean.WxMpCustomMessage;

public class BaseBuilder<T> {
	protected String msgType;
	protected String toUser;

	@SuppressWarnings("unchecked")
	public T toUser(String toUser) {
		this.toUser = toUser;
		return (T) this;
	}

	public WxMpCustomMessage build() {
		WxMpCustomMessage m = new WxMpCustomMessage();
		m.setMsgType(this.msgType);
		m.setToUser(this.toUser);
		return m;
	}
}
