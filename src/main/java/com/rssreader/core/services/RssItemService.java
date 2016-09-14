package com.rssreader.core.services;


import com.rssreader.core.models.entities.RssItem;
import com.rssreader.core.services.util.RssItemList;

public interface RssItemService {
    public RssItem findRssItem(Long id);
    public RssItem createRssItem(RssItem data);
    public RssItemList findAllRssItems();
    public RssItem findByRssItemTitle(String title);
}
