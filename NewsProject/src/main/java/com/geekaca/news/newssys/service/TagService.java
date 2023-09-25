package com.geekaca.news.newssys.service;

import com.geekaca.news.newssys.domain.TagNewsCount;

import java.util.List;

public interface TagService {
    int getTotalTags();
    List<TagNewsCount> getAll();
}
