package com.rssreader.core.services.impl;

import com.rssreader.core.models.entities.RssItem;
import com.rssreader.core.repositories.RssItemRepo;
import com.rssreader.core.services.exceptions.RssItemExistsException;
import com.rssreader.core.services.util.RssItemList;
import com.rssreader.core.services.RssItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Domain Logic
@Service
@Transactional
public class RssItemServiceImpl implements RssItemService {

    @Autowired
    private RssItemRepo rssItemRepo;

    @Override
    public RssItem findRssItem(Long id) {
        return rssItemRepo.findRssItem(id);
    }

    @Override
    public RssItem createRssItem(RssItem data) {
        RssItem rssItem = rssItemRepo.findRssItemByTitle(data.getTitle());
        if(rssItem != null)
        {
            throw new RssItemExistsException();
        }
        return rssItemRepo.createRssItem(data);
    }

    @Override
    public RssItemList findAllRssItems() {
        return new RssItemList(rssItemRepo.findAllRssItems());
    }

    @Override
    public RssItem findByRssItemTitle(String title) {
        return rssItemRepo.findRssItemByTitle(title);
    }
}
