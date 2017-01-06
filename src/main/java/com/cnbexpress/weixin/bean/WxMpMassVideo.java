package com.cnbexpress.weixin.bean;

import com.cnbexpress.weixin.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * 群发时用到的视频素材
 * 
 * @author tom.tao
 */
public class WxMpMassVideo implements Serializable {
	private static final long serialVersionUID = -8467297131705515384L;

	private String mediaId;
	private String title;
	private String description;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String toJson() {
		return WxMpGsonBuilder.INSTANCE.create().toJson(this);
	}
}
