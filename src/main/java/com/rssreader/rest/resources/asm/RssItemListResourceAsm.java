package com.rssreader.rest.resources.asm;

import com.rssreader.core.services.util.RssItemList;
import com.rssreader.rest.mvc.RssItemController;
import com.rssreader.rest.resources.RssItemListResource;
import com.rssreader.rest.resources.RssItemResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

public class RssItemListResourceAsm extends ResourceAssemblerSupport<RssItemList, RssItemListResource> {


    public RssItemListResourceAsm() {
        super(RssItemController.class, RssItemListResource.class);
    }

    @Override
    public RssItemListResource toResource(RssItemList RssItemList) {
        List<RssItemResource> resList = new RssItemResourceAsm().toResources(RssItemList.getRssItems());
        RssItemListResource finalRes = new RssItemListResource();
        finalRes.setRssItems(resList);
        return finalRes;
    }
}
