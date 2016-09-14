package com.rssreader.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

public class RssItemListResource extends ResourceSupport {
    private List<RssItemResource> rssItems = new ArrayList<RssItemResource>();

    public List<RssItemResource> getRssItems() {
        return rssItems;
    }

    public void setRssItems(List<RssItemResource> rssItems) {
        this.rssItems = rssItems;
    }
}
