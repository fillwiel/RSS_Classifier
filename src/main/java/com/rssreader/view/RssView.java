package com.rssreader.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.rssreader.model.Document;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;
import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Content;
import com.rometools.rome.feed.rss.Item;

public class RssView extends AbstractRssFeedView {

    @Override
    protected void buildFeedMetadata(Map<String, Object> model,
                                     Channel channel,
                                     HttpServletRequest request) {

        channel.setTitle("Spring MVC RSS ATOM Content Negotiation Example");
        channel.setLink("http://memorynotfound.com/spring-mvc-rss-atom-content-negotiation/");
        channel.setDescription("RSS ATOM View Resolvers");
        channel.setLastBuildDate(new Date());
        channel.setTtl(1800);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected List<Item> buildFeedItems(Map<String, Object> model,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        List<Item> items = new ArrayList<Item>();
        Object ob = model.get("documents");
        if (ob instanceof List){
            List<Document> documents = (List<Document>)ob;
            for (Document document : documents){

                // content item
                Content content = new Content();
                content.setValue(document.getDescription());

                // item
                Item item = new Item();
                item.setTitle(document.getTitle());
                item.setContent(content);
                item.setPubDate(document.getPubDate());
                item.setLink(document.getLink());
                items.add(item);
            }
        }
        return items;
    }
}