package com.geekaca.news.newssys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class News {
    private Long newsId;
    private String newsSubUrl;
    private String newsTitle;
    private String newsCoverImage;
    private Integer newsCategoryId;
    private String newsCategoryName;
    private String newsTags;
    private Byte newsStatus;
    //阅读量
    private Long newsViews;
    //是否允许评论
    private Byte enableComment;
    private Byte isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Date updateTime;
    private String newsContent;
}
