package com.graph.article;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graph.comment.Comment;
import com.graph.comment.CommentRepository;
import com.graph.profile.Profile;
import com.graph.profile.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ArticleResolver implements GraphQLResolver<Article> {
    private CommentRepository commentRepository;
    private ProfileRepository profileRepository;

    public Profile getAuthor(Article article) {
        Optional<Profile> optional = profileRepository.findById(article.getAuthorId());
        return optional.get();
    }

    public List<Comment> getComments(Article article) {
        return commentRepository.findByArticleId(article.getId());
    }
}
