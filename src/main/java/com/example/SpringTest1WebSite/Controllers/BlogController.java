package com.example.SpringTest1WebSite.Controllers;

import com.example.SpringTest1WebSite.dao.PostArticleRepository;
import com.example.SpringTest1WebSite.models.PostArticle;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

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
    @GetMapping("/blog/add")
    public String blogAddArticle (Model model){
        Iterable<PostArticle> postArticles = postArticleRepository.findAll();
        model.addAttribute("postArticles", postArticles);
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostNewArticle (
            @RequestParam String title,
            @RequestParam String anons,
            @RequestParam String full_text,
            Model model ){
        PostArticle postArticle = new PostArticle(title, anons, full_text);
        postArticleRepository.save(postArticle);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{idOfTheArticle}")
    public String blogShowMoreOfTheArticle (
            @PathVariable(value = "idOfTheArticle") long idOfTheArticle, Model model) {
        if(!postArticleRepository.existsById(idOfTheArticle)){
            return "redirect:/blog";
        }

        Optional<PostArticle> postArticle =  postArticleRepository.findById(idOfTheArticle);
        ArrayList<PostArticle> postArticleArrayList = new ArrayList<>();

        postArticle.ifPresent(postArticleArrayList::add);

        model.addAttribute("postArticle", postArticleArrayList);
        return "blog-details";

    }
    @GetMapping("/blog/{idOfTheArticle}/edit")
    public String blogEditTheArticle (
            @PathVariable(value = "idOfTheArticle") long idOfTheArticle, Model model) {
        if(!postArticleRepository.existsById(idOfTheArticle)){
            return "redirect:/blog";
        }

        Optional<PostArticle> postArticle = postArticleRepository.findById(idOfTheArticle);
        ArrayList<PostArticle> postArticleArrayList = new ArrayList<>();

        postArticle.ifPresent(postArticleArrayList::add);

        model.addAttribute("postArticle", postArticleArrayList);
        return "blog-edit";
    }
    @PostMapping("/blog/{idOfTheArticle}/edit")
    public String blogUpdateCurrentArticle (
            @PathVariable(value = "idOfTheArticle") long idOfTheArticle,
            @RequestParam String title,
            @RequestParam String anons,
            @RequestParam String full_text,
            Model model ){
        PostArticle postArticle = postArticleRepository.findById(idOfTheArticle).orElseThrow();

        postArticle.setTitle(title);
        postArticle.setAnons(anons);
        postArticle.setFull_text(full_text);
        postArticleRepository.save(postArticle);

        return "redirect:/blog";
    }


}
