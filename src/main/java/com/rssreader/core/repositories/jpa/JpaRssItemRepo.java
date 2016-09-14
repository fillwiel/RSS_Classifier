package com.rssreader.core.repositories.jpa;


import com.rssreader.core.models.entities.RssItem;
import com.rssreader.core.repositories.RssItemRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository //allows to inject spring singleton (similar to @Component, but gives more possibilities)
public class JpaRssItemRepo implements RssItemRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<RssItem> findAllRssItems() {
        Query query = em.createQuery("SELECT a FROM RssItem a");
        return query.getResultList();
    }

    @Override
    public RssItem findRssItem(Long id) {
        return em.find(RssItem.class, id);
    }

    @Override
    public RssItem findRssItemByTitle(String title) {
        Query query = em.createQuery("SELECT a FROM RssItem a WHERE a.title=?1");
        query.setParameter(1, title);
        List<RssItem> rssItems = query.getResultList();
        if(rssItems.size() == 0) {
            return null;
        } else {
            return rssItems.get(0);
        }
    }

    @Override
    public RssItem createRssItem(RssItem data) {
        em.persist(data);
        return data;
    }
}
