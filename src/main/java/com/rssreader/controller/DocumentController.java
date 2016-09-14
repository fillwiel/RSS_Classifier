package com.rssreader.controller;

import com.rssreader.model.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/documents")
public class DocumentController {

    List<Document> documents = Arrays.asList(
            new Document("1", "title1", "description1", new Date(), "http://memorynotfound.com"),
            new Document("2", "title2", "description2", new Date(), "http://memorynotfound.com")
    );

    @RequestMapping(method = RequestMethod.GET)
    public String getDocuments(Model model) {
        model.addAttribute("documents", documents);
        return "index";

    }
}