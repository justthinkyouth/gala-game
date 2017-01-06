package com.cnbexpress.weixin.bean.outxmlbuilder;

import com.cnbexpress.weixin.bean.WxMpXmlOutTransferCustomerServiceMessage;

/**
 * 客服消息builder
 * <pre>
 * 用法: WxMpCustomMessage m = WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE().content(...).toUser(...).build();
 * </pre>
 *
 * @author tom.tao
 */
public final class TransferCustomerServiceBuilder extends BaseBuilder<TransferCustomerServiceBuilder, WxMpXmlOutTransferCustomerServiceMessage> {
  private String kfAccount;

  public TransferCustomerServiceBuilder kfAccount(String kfAccount) {
    this.kfAccount = kfAccount;
    return this;
  }


  public WxMpXmlOutTransferCustomerServiceMessage build() {
    WxMpXmlOutTransferCustomerServiceMessage m = new WxMpXmlOutTransferCustomerServiceMessage();
    setCommon(m);
    m.setKfAccount(kfAccount);
    return m;
  }
}
