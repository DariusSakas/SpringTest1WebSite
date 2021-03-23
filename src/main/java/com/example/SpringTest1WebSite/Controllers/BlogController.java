package com.example.SpringTest1WebSite.Controllers;

import com.example.SpringTest1WebSite.dao.PostArticleRepository;
import com.example.SpringTest1WebSite.models.PostArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {

    @Autowired
    private PostArticleRepository postArticleRepository;

    @GetMapping("/blog")
    public String blogMain (Model model){
        Iterable<PostArticle> postArticles = postArticleRepository.findAll();
        model.addAttribute("postArticles", postArticles);
        return "blog-main";
    }
}
