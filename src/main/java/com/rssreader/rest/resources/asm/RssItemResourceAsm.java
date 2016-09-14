package com.rssreader.rest.resources.asm;

import com.rssreader.core.models.entities.RssItem;
import com.rssreader.rest.mvc.RssItemController;
import com.rssreader.rest.resources.RssItemResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class RssItemResourceAsm extends ResourceAssemblerSupport<RssItem, RssItemResource> {
    public RssItemResourceAsm() {
        super(RssItemController.class, RssItemResource.class);
    }

    @Override
    public RssItemResource toResource(RssItem RssItem) {
        RssItemResource res = new RssItemResource();
        res.setTitle(RssItem.getTitle());
        res.setDescription(RssItem.getDescription());
        res.setRid(RssItem.getId());
        res.add(linkTo(methodOn(RssItemController.class).getRssItem(RssItem.getId())).withSelfRel());
        return res;
    }
}
