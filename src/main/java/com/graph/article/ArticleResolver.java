package com.graph.article;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graph.comment.Comment;
import com.graph.comment.CommentRepository;
import com.graph.profile.Profile;
import com.graph.profile.ProfileRepository;
import graphql.execution.batched.Batched;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Component
@AllArgsConstructor
@Slf4j
public class ArticleResolver implements GraphQLResolver<Article> {
    private CommentRepository commentRepository;
    private ProfileRepository profileRepository;


    public Profile getAuthor(Article article) {
        Optional<Profile> optional = profileRepository.findById(article.getAuthorId());
        log.info("Fetching for Article with author :::::::" +article.getId());
        return optional.get();
    }

    //TODO check for commentsId in article
//    @Batched
    public List<Comment> getComments(Article article) {
        log.info("Fetching for Article with comments :::::::" +article.getId());
        CompletableFuture.supplyAsync(()->{
            return commentRepository.findByArticleId(article.getId());
        });
        return commentRepository.findByArticleId(article.getId());
    }
}
