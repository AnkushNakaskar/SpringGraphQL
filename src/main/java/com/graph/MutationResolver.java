package com.graph;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.graph.article.*;
import com.graph.comment.Comment;
import com.graph.comment.CommentRepository;
import com.graph.comment.CreateCommentInput;
import com.graph.profile.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class MutationResolver implements GraphQLMutationResolver {
    private ArticleRepository articleRepository;
    private CommentRepository commentRepository;
    private ProfileRepository profileRepository;

    @Transactional
    public Article createArticle(CreateArticleInput input) {
        return articleRepository.saveAndFlush(new Article(null, input.getTitle(), input.getText(), input.getAuthorId()));
    }

    @Transactional
    public Article updateArticle(UpdateArticleInput input) {
        Article article = articleRepository
            .findById(input.getId())
            .orElseThrow(() -> new ArticleNotFoundException(input.getId()));
        article.setText(input.getText());
        article.setTitle(input.getTitle());
        return article;
    }

    @Transactional
    public void deleteArticle(Long id) {
         articleRepository.deleteById(id);
    }

    @Transactional
    public Profile createProfile(CreateProfileInput input) {
        return profileRepository.saveAndFlush(new Profile(null, input.getUsername(), input.getBio()));
    }

    @Transactional
    public Profile updateProfile(UpdateProfileInput input) {
        Profile profile = profileRepository
            .findById(input.getId())
            .orElseThrow(() -> new ProfileNotFoundException(input.getId()));
        profile.setBio(input.getBio());
        return profile;
    }

    @Transactional
    public void deleteProfile(Long id) {
         profileRepository.deleteById(id);
    }

    @Transactional
    public Comment createComment(CreateCommentInput input) {
        return commentRepository.saveAndFlush(new Comment(null, input.getText(), input.getArticleId(), input.getAuthorId()));
    }

    @Transactional
    public void deleteComment(Long id) {
         commentRepository.deleteById(id);
    }
}
