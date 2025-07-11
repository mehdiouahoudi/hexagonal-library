package com.ouahoudi.library.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Author {
    private Long id;
    private String name;
    private int age;
    private int followersNumber;

    @JsonCreator
    public Author(@JsonProperty("id") Long id, @JsonProperty("name") String name, @JsonProperty("age") int age, @JsonProperty("followersNumber") int followersNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.followersNumber = followersNumber;
    }

    public Author() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getFollowersNumber() {
        return followersNumber;
    }

    public void setFollowersNumber(int followersNumber) {
        this.followersNumber = followersNumber;
    }
}
