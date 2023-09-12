package com.geekaca.news.newssys.service.impl;


import com.geekaca.news.newssys.dao.NewsLinkMapper;
import com.geekaca.news.newssys.domain.BlogLink;
import com.geekaca.news.newssys.service.LinkService;
import com.geekaca.news.newssys.utils.PageQueryUtil;
import com.geekaca.news.newssys.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private NewsLinkMapper newsLinkMapper;

    @Override
    public PageResult getBlogLinkPage(PageQueryUtil pageUtil) {
        List<BlogLink> links = newsLinkMapper.findLinkList(pageUtil);
        int total = newsLinkMapper.getTotalLinks(pageUtil);
        PageResult pageResult = new PageResult(links, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public int getTotalLinks() {
        return newsLinkMapper.getTotalLinks(null);
    }

    @Override
    public Boolean saveLink(BlogLink link) {
        return newsLinkMapper.insertSelective(link) > 0;
    }

    @Override
    public BlogLink selectById(Integer id) {
        return newsLinkMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean updateLink(BlogLink tempLink) {
        return newsLinkMapper.updateByPrimaryKeySelective(tempLink) > 0;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return newsLinkMapper.deleteBatch(ids) > 0;
    }

    @Override
    public Map<Byte, List<BlogLink>> getLinksForLinkPage() {
        //获取所有链接数据
        List<BlogLink> links = newsLinkMapper.findLinkList(null);
        if (!CollectionUtils.isEmpty(links)) {
            //根据type进行分组
            Map<Byte, List<BlogLink>> linksMap = links.stream().collect(Collectors.groupingBy(BlogLink::getLinkType));
            return linksMap;
        }
        return null;
    }
}
