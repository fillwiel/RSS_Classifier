package com.rssreader.rest.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rssreader.core.models.entities.RssItem;
import org.springframework.hateoas.ResourceSupport;

public class RssItemResource extends ResourceSupport {
    private String title;

    private String description;

    private Long rid;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonIgnore
    public String getDescription() {
        return description;
    }

    @JsonProperty
    public void setDescription(String description) {
        this.description = description;
    }

    public RssItem toRssItem() {
        RssItem rssItem = new RssItem();
        rssItem.setTitle(title);
        rssItem.setDescription(description);
        return rssItem;
    }
}
