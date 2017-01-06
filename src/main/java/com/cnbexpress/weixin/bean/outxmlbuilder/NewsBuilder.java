package com.cnbexpress.weixin.bean.outxmlbuilder;

import com.cnbexpress.weixin.bean.WxMpXmlOutNewsMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * 图文消息builder
 * @author tom.tao
 */
public final class NewsBuilder extends BaseBuilder<NewsBuilder, WxMpXmlOutNewsMessage> {

  protected final List<WxMpXmlOutNewsMessage.Item> articles = new ArrayList<WxMpXmlOutNewsMessage.Item>();
  
  public NewsBuilder addArticle(WxMpXmlOutNewsMessage.Item item) {
    this.articles.add(item);
    return this;
  }
  public NewsBuilder addArticles(List<WxMpXmlOutNewsMessage.Item> items) {
    for (WxMpXmlOutNewsMessage.Item item: items) {
      this.articles.add(item);
    }
    return this;
  }
  
  public WxMpXmlOutNewsMessage build() {
    WxMpXmlOutNewsMessage m = new WxMpXmlOutNewsMessage();
    for(WxMpXmlOutNewsMessage.Item item : articles) {
      m.addArticle(item);
    }
    setCommon(m);
    return m;
  }
  
}
