package com.geekaca.news.newssys.domain;

import lombok.Data;

import java.util.Date;
@Data
public class NewsTagRelation {
    private Long relationId;

    private Long blogId;

    private Integer tagId;

    private Date createTime;
}
