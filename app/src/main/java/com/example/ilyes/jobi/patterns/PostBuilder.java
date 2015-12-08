package com.example.ilyes.jobi.patterns;

import com.example.ilyes.jobi.models.Post;

public class PostBuilder {
    private long id;
    private String title;
    private String text;
    private long userOwnerId;

    public PostBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public PostBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public PostBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public PostBuilder setUserOwnerId(long userOwnerId) {
        this.userOwnerId = userOwnerId;
        return this;
    }

    public Post build() {
        return new Post(id, title, text, userOwnerId);
    }
}