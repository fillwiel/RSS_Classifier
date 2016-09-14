package com.rssreader.view;

import com.rssreader.model.Document;
import com.rometools.rome.feed.atom.Content;
import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.atom.Feed;
import com.rometools.rome.feed.atom.Link;
import com.rometools.rome.feed.synd.SyndPerson;
import com.rometools.rome.feed.synd.SyndPersonImpl;
import org.springframework.web.servlet.view.feed.AbstractAtomFeedView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class AtomView extends AbstractAtomFeedView {

    @Override
    protected void buildFeedMetadata(Map<String, Object> model, Feed feed,
                                     HttpServletRequest request) {

        // content
        Content content = new Content();
        content.setValue("ATOM View");
        feed.setSubtitle(content);
        feed.setTitle("Spring MVC RSS ATOM Content Negotiation Example");

        // links
        Link link = new Link();
        link.setHref("http://memorynotfound.com/feed");
        link.setRel("self");
        link.setTitle("ATOM feed URL");
        feed.setAlternateLinks(Arrays.asList(link));

        // meta-data
        feed.setUpdated(new Date());
    }

    @Override
    @SuppressWarnings("unchecked")
    protected List<Entry> buildFeedEntries(Map<String, Object> model,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {

        List<Entry> entries = new ArrayList<Entry>();
        Object ob = model.get("documents");
        if (ob instanceof List) {
            List<Document> documents = (List<Document>) ob;
            for (Document document : documents) {

                // entry
                Entry entry = new Entry();
                entry.setId(document.getFeedId());
                entry.setPublished(document.getPubDate());
                entry.setTitle(document.getTitle());

                // content
                Content content = new Content();
                content.setValue(document.getDescription());
                entry.setSummary(content);

                // links
                Link link = new Link();
                link.setHref(document.getLink());
                entry.setAlternateLinks(Arrays.asList(link));

                // authors
                SyndPerson author = new SyndPersonImpl();
                author.setName("Memorynotfound");
                author.setEmail("info@memorynotfound.com");
                entry.setAuthors(Arrays.asList(author));

                entries.add(entry);
            }
        }

        return entries;
    }
}
