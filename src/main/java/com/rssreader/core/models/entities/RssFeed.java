package com.rssreader.core.models.entities;

import java.util.ArrayList;
import java.util.List;

public class RssFeed {
    private List<String> titles = new ArrayList<String>();
    private List<String> descriptions = new ArrayList<String>();

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<String> descriptions) {
        this.descriptions = descriptions;
    }

    public void addTitle(String title) {
        this.titles.add(title);
    }
    public void addDescription(String description) {
        this.descriptions.add(description);
    }

}
