package com.hodoledu.domain;

import lombok.Builder;
import lombok.Getter;

import jakarta.persistence.Entity;

@Getter
public class PostEditor {


    private final String title;
    private final String content;

    @Builder
    public PostEditor(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
