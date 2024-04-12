package com.tweet.cogent.tweet.app.service.impl;

import com.tweet.cogent.tweet.app.entity.Tag;
import com.tweet.cogent.tweet.app.repository.TagRepository;
import com.tweet.cogent.tweet.app.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;
    @Override
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag getTagById(Long tagId) {
        return tagRepository.findById(tagId).orElseThrow(() -> new RuntimeException("Tag not found"));
    }

    @Override
    public List<Tag> getAllTag() {
        return tagRepository.findAll();
    }

    @Override
    public void deleteTag(Long tagId) {
        tagRepository.findById(tagId).orElseThrow(() -> new RuntimeException("Tag not found"));
        tagRepository.deleteById(tagId);
        //logging purposes, delete later
        System.out.println("Tag successfully deleted");
    }
}
