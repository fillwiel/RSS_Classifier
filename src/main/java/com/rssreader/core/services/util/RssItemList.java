package com.rssreader.core.services.util;

import com.rssreader.core.models.entities.RssItem;

import java.util.ArrayList;
import java.util.List;

public class RssItemList {

    private List<RssItem> rssItems = new ArrayList<RssItem>();

    public RssItemList(List<RssItem> list) {
        this.rssItems = list;
    }

    public List<RssItem> getRssItems() {
        return rssItems;
    }

    public void setRssItems(List<RssItem> accounts) {
        this.rssItems = accounts;
    }
}
