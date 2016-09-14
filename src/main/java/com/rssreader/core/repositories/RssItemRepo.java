package com.rssreader.core.repositories;


import com.rssreader.core.models.entities.RssItem;

import java.util.List;

public interface RssItemRepo {
    List<RssItem> findAllRssItems();
    RssItem findRssItem(Long id);
    RssItem findRssItemByTitle(String title);
    RssItem createRssItem(RssItem data);

}
