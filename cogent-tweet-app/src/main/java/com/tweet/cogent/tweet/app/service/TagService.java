package com.tweet.cogent.tweet.app.service;

import com.tweet.cogent.tweet.app.entity.Tag;

import java.util.List;

public interface TagService {
    Tag createTag (Tag tag);

    Tag getTagById(Long tagId);

    List<Tag> getAllTag();

    void deleteTag(Long tagId);
}
