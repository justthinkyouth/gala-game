package com.cnbexpress.weixin.bean.custombuilder;

import com.cnbexpress.weixin.bean.WxMpCustomMessage;
import com.cnbexpress.weixin.common.api.WxConsts;

/**
 * 文本消息builder
 * <pre>
 * 用法: WxMpCustomMessage m = WxMpCustomMessage.TEXT().content(...).toUser(...).build();
 * </pre>
 * @author tom.tao
 *
 */
public final class TextBuilder extends BaseBuilder<TextBuilder> {
  private String content;

  public TextBuilder() {
    this.msgType = WxConsts.CUSTOM_MSG_TEXT;
  }

  public TextBuilder content(String content) {
    this.content = content;
    return this;
  }

  public WxMpCustomMessage build() {
    WxMpCustomMessage m = super.build();
    m.setContent(this.content);
    return m;
  }
}
