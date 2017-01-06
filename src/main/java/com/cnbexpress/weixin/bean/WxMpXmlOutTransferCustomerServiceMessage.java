package com.cnbexpress.weixin.bean;

import com.cnbexpress.weixin.common.api.WxConsts;
import com.cnbexpress.weixin.common.util.xml.XStreamCDataConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("xml")
public class WxMpXmlOutTransferCustomerServiceMessage extends WxMpXmlOutMessage {
	private static final long serialVersionUID = 7567666858367690499L;

	@XStreamAlias("TransInfo")
	protected final TransInfo transInfo = new TransInfo();

	public WxMpXmlOutTransferCustomerServiceMessage() {
		this.msgType = WxConsts.CUSTOM_MSG_TRANSFER_CUSTOMER_SERVICE;
	}

	public String getKfAccount() {
		return transInfo.getKfAccount();
	}

	public void setKfAccount(String kfAccount) {
		transInfo.setKfAccount(kfAccount);
	}

	@XStreamAlias("TransInfo")
	public static class TransInfo {

		@XStreamAlias("KfAccount")
		@XStreamConverter(value = XStreamCDataConverter.class)
		private String kfAccount;

		public String getKfAccount() {
			return kfAccount;
		}

		public void setKfAccount(String kfAccount) {
			this.kfAccount = kfAccount;
		}
	}
}
