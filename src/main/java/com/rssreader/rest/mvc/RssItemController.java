package com.rssreader.rest.mvc;

import com.rssreader.core.models.entities.RssFeed;
import com.rssreader.core.models.entities.RssItem;
import com.rssreader.core.services.RssItemService;
import com.rssreader.core.services.exceptions.RssItemExistsException;
import com.rssreader.core.services.util.RssItemList;
import com.rssreader.reader.Reader;
import com.rssreader.rest.exceptions.ConflictException;
import com.rssreader.rest.resources.RssItemListResource;
import com.rssreader.rest.resources.RssItemResource;
import com.rssreader.rest.resources.asm.RssItemListResourceAsm;
import com.rssreader.rest.resources.asm.RssItemResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/rest/rssItems")
public class RssItemController {
    private RssItemService rssItemService;

    @Autowired
    public RssItemController(RssItemService rssItemService) {
        this.rssItemService = rssItemService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<RssItemListResource> findAllRssItems(@RequestParam(value="title", required = false) String title, @RequestParam(value="description", required = false) String description) {
        RssItemList list = null;
        if(title == null) {
            list = rssItemService.findAllRssItems();
        } else {
            RssItem rssItem = rssItemService.findByRssItemTitle(title);
            list = new RssItemList(new ArrayList<RssItem>());
            if(rssItem != null) {
                if(description != null) {
                    if(rssItem.getDescription().equals(description)) {
                        list = new RssItemList(Arrays.asList(rssItem));
                    }
                } else {
                    list = new RssItemList(Arrays.asList(rssItem));
                }
            }
        }
        RssItemListResource res = new RssItemListResourceAsm().toResource(list);
        return new ResponseEntity<RssItemListResource>(res, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("permitAll")
    public ResponseEntity<RssItemResource> createRssItem(
            @RequestBody RssItemResource sentRssItem
    ) {
        try {
            RssItem createdRssItem = rssItemService.createRssItem(sentRssItem.toRssItem());
            RssItemResource res = new RssItemResourceAsm().toResource(createdRssItem);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(res.getLink("self").getHref()));
            return new ResponseEntity<RssItemResource>(res, headers, HttpStatus.CREATED);
        } catch(RssItemExistsException exception) {
            throw new ConflictException(exception);
        }
    }

    @RequestMapping( value="/{rssItemId}",
                method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<RssItemResource> getRssItem(
            @PathVariable Long rssItemId
    ) {
        RssItem rssItem = rssItemService.findRssItem(rssItemId);
        if(rssItem != null)
        {
            RssItemResource res = new RssItemResourceAsm().toResource(rssItem);
            return new ResponseEntity<RssItemResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<RssItemResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping( value="/rssgo",
            method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public void rssGo() {
        RssFeed rssFeed = Reader.readRssFeed("http://feeds.feedburner.com/dobreprogramy/Aktualnosci");
        RssItemResource rssItemResource = new RssItemResource();

        for (int i = 0; i < rssFeed.getTitles().size() ; i++){
            rssItemResource.setTitle(rssFeed.getTitles().get(i));
            rssItemResource.setDescription(rssFeed.getDescriptions().get(i));
            createRssItem(rssItemResource);
        }
    }
}
