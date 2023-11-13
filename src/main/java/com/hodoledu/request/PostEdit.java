package com.hodoledu.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class PostEdit {

    //값 검증을 해줌 빈값이 넘어오면 에러유발
    @NotBlank(message = "타이틀을 입력하시오.")
    private String title;
    @NotBlank(message = "콘텐츠입력하시오.")
    private String content;


    @Builder
    public PostEdit(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
