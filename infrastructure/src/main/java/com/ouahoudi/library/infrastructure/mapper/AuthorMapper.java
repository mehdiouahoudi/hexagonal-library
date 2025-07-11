package com.ouahoudi.library.infrastructure.mapper;

import com.ouahoudi.library.domain.model.Author;
import com.ouahoudi.library.infrastructure.entity.AuthorEntity;

public class AuthorMapper {

    private AuthorMapper() {
    }

    public static Author toDomain(AuthorEntity entity) {
        return new Author(entity.getId(), entity.getName(), entity.getAge(), entity.getFollowersNumber());
    }

    public static AuthorEntity toEntity(Author author) {
        return new AuthorEntity(author.getId(), author.getName(), author.getAge(), author.getFollowersNumber());
    }
}

