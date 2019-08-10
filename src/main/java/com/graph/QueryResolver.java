package com.graph;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.graph.article.Article;
import com.graph.article.ArticleRepository;
import com.graph.comment.Comment;
import com.graph.comment.CommentRepository;
import com.graph.profile.Profile;
import com.graph.profile.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class QueryResolver implements GraphQLQueryResolver {
    private ArticleRepository articleRepository;
    private CommentRepository commentRepository;
    private ProfileRepository profileRepository;

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public List<Profile> getProfiles() {
        return profileRepository.findAll();
    }

    public Article getArticle(Long id) {
        return articleRepository.findOne(id);
    }
}
