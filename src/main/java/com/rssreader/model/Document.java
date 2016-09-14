package com.rssreader.model;

import java.util.Date;

public class Document {

    private String feedId;
    private String title;
    private String description;
    private Date pubDate;
    private String link;

    public Document(String feedId, String title, String description, Date pubDate, String link) {
        this.feedId = feedId;
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.link = link;
    }

    public String getFeedId() {
        return feedId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public String getLink() {
        return link;
    }
}