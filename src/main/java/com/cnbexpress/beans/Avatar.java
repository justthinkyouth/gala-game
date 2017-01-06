package com.cnbexpress.beans;

/**
 * Created by tom on 17-1-6.
 */
public class Avatar {

    private String url;
    private long size;
    private String fileName;
    private String suffix;

    public Avatar(String url, long size, String fileName, String suffix) {
        this.url = url;
        this.size = size;
        this.fileName = fileName;
        this.suffix = suffix;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
