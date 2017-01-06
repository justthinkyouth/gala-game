/*
 * KINGSTAR MEDIA SOLUTIONS Co.,LTD. Copyright c 2005-2013. All rights reserved.
 *
 * This source code is the property of KINGSTAR MEDIA SOLUTIONS LTD. It is intended
 * only for the use of KINGSTAR MEDIA application development. Reengineering, reproduction
 * arose from modification of the original source, or other redistribution of this source
 * is not permitted without written permission of the KINGSTAR MEDIA SOLUTIONS LTD.
 */
package com.cnbexpress.weixin.util.json;

import com.cnbexpress.weixin.bean.result.WxEMassSendResult;
import com.cnbexpress.weixin.common.util.json.GsonHelper;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * 
 * @author tom.tao
 *
 */
public class WxEMassSendResultAdapter implements JsonDeserializer<WxEMassSendResult> {

  public WxEMassSendResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    WxEMassSendResult sendResult = new WxEMassSendResult();
    JsonObject sendResultJsonObject = json.getAsJsonObject();

    if (sendResultJsonObject.get("errcode") != null && !sendResultJsonObject.get("errcode").isJsonNull()) {
      sendResult.setErrorCode(GsonHelper.getAsString(sendResultJsonObject.get("errcode")));
    }
    if (sendResultJsonObject.get("errmsg") != null && !sendResultJsonObject.get("errmsg").isJsonNull()) {
      sendResult.setErrorMsg(GsonHelper.getAsString(sendResultJsonObject.get("errmsg")));
    }
    return sendResult;
  }
  
}
