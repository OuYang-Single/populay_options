package com.pine.populay_options.mvp.model.entity;

public class PushMessage {
    private    String pushTopic;
    private  String pushContent;
    private  long createTime;
    private  String url;

    //Bundle[{google.delivered_priority=normal, google.sent_time=1609084154396, google.ttl=2419200, google.original_priority=normal, data={"createTime":1609084151963,"pushTopic":"topic","pushContent":"正式链接，可以测试谷歌登录，上传图片","url":"https:\/\/eto.master365pro.com\/"}, from=175718795225, google.message_id=0:1609084154408388%9f726f5ff9fd7ecd, google.c.sender.id=175718795225}]
    public String getPushTopic() {
        return pushTopic;
    }

    public void setPushTopic(String pushTopic) {
        this.pushTopic = pushTopic;
    }

    public String getPushContent() {
        return pushContent;
    }

    public void setPushContent(String pushContent) {
        this.pushContent = pushContent;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
