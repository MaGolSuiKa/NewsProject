package com.geekaca.news.newssys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 类别
 */
@Data
public class NewsCategory {
    private Integer categoryId;

    private String categoryName;

    private String categoryIcon;

    private Integer categoryRank;

    private Byte isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
