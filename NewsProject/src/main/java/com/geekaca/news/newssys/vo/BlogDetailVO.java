package com.geekaca.news.newssys.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class BlogDetailVO {
    private Long newsId;

    private String newsTitle;

    private Integer newsCategoryId;

    private Integer commentCount;

    private String newsCategoryIcon;

    private String newsCategoryName;

    private String newsCoverImage;

    private Long newsViews;

    private List<String> newsTags;

    private String newsContent;

    private Byte enableComment;

    private Date createTime;


}
