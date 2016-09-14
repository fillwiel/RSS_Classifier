package com.rssreader.core.repositories;

import com.rssreader.core.models.entities.RssItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

// tests RunWith unit
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@WebAppConfiguration
public class RssItemTest {

    @Autowired
    private RssItemRepo repo;

    private RssItem rssItem;

    @Before
    @Transactional
    @Rollback(false)
    public void setup(){
        rssItem = new RssItem();
        rssItem.setTitle("title 1");
        rssItem.setDescription("This is the description for item 1");
        repo.createRssItem(rssItem);
    }

    @Test
    @Transactional
    public void test(){
        assertNotNull(repo.findRssItem(rssItem.getId()));
    }

}
