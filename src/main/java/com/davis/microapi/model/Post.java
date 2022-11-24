package com.davis.microapi.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import net.bytebuddy.asm.Advice.Local;

@Entity
public class Post {
    
@Id @GeneratedValue
private long id;
private String title;
private String slug;
private String content;
private String author;
private LocalDateTime publishedOn;
private LocalDateTime updatedOn;

public Post() {

}

public Post( 
    String title,
    String slug,
    String content,
    String author
 ) {

    this.title = title;
    this.slug = slug;
    this.content = content;
    this.author = author;
    this.publishedOn = LocalDateTime.now();

}


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return this.slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getPublishedOn() {
        return this.publishedOn;
    }

    public void setPublishedOn(LocalDateTime publishedOn) {
        this.publishedOn = publishedOn;
    }

    public LocalDateTime getUpdatedOn() {
        return this.updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", slug='" + getSlug() + "'" +
            ", content='" + getContent() + "'" +
            ", author='" + getAuthor() + "'" +
            ", publishedOn='" + getPublishedOn() + "'" +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }


}
