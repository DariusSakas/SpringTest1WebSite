package com.example.SpringTest1WebSite.dao;

import com.example.SpringTest1WebSite.models.PostArticle;
import org.springframework.data.repository.CrudRepository;

public interface PostArticleRepository extends CrudRepository <PostArticle, Long>{



}
