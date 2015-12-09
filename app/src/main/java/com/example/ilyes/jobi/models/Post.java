package com.example.ilyes.jobi.models;

/**
 * Created by ilyes on 08/12/15.
 */
public class Post {

    private long id;
    private String title;
    private String text;
    private long userOwnerId;

    public Post() {

    }


    public Post(long id, String title, String text, long userOwnerId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.userOwnerId = userOwnerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getUserOwnerId() {
        return userOwnerId;
    }

    public void setUserOwnerId(long userOwnerId) {
        this.userOwnerId = userOwnerId;
    }


    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", userOwnerId=" + userOwnerId +
                '}';
    }
}
