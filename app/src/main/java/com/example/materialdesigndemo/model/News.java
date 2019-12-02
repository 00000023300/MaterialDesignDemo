package com.example.materialdesigndemo.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;
public class News implements Serializable {
    /**
     * uniquekey : 30751cafec4d9a74310d7a74c4384d12
     * title : 澳大利亚东部发生“史无前例”林火，2人死亡多栋房屋被毁
     * date : 2019-11-09 18:43
     * category : 头条
     * author_name : 澎湃新闻网
     * url : http://mini.eastday.com/mobile/191109184348716.html
     * thumbnail_pic_s : http://01imgmini.eastday.com/mobile/20191109/20191109184348_b5beb48bcccc35b53caf82bf5fb50aba_1_mwpm_03200403.jpg
     */
    private String uniqueKey;
    private String title;

    @JSONField(format = "yyyy-MM-dd hh:mm")
    private Date date;

    private String category;

    @JSONField(name = "author_name")
    private String authorName;

    private String url;

    @JSONField(name = "thumbnail_pic_s")
    private String pic;

    public String getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}