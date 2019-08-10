package com.graph.comment;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graph.profile.Profile;
import com.graph.profile.ProfileRepository;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CommentResolver implements GraphQLResolver<Comment> {
    private ProfileRepository profileRepository;

    public Profile getAuthor(Comment comment) {
        Optional<Profile> profile = profileRepository.findById(comment.getAuthorId());
//        GraphQLObjectType type= GraphQLObjectType.newObject()
//        GraphQLSchema.newSchema().
//        https://stackoverflow.com/questions/36759503/hello-world-with-graphqlobjecttype
        return profile.get();
    }
}
