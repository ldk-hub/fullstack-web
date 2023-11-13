package com.hodoledu.request;

import com.hodoledu.exception.InvalidRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class PostCreate {

    //빌더의 장접
    // 가독성에 좋다. (값 생성의 유연성)
    // 필요한 값만 받을 수 있다. // -> (오버로딩 가능 조건 찾아보기)
    // 객체의 불변성

    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //값 검증을 해줌 빈값이 넘어오면 에러유발
    @NotBlank(message = "타이틀을 입력하시오.")
    private String title;
    @NotBlank(message = "콘텐츠입력하시오.")
    private String content;


    public void validated() {
        if (title.contains("바보")) {
            throw new InvalidRequest("title","제목에 바보는 포함할 수 없습니다.");
        }
    }
}
