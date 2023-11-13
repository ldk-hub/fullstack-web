package com.hodoledu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hodoledu.domain.Post;
import com.hodoledu.repository.PostRepository;
import com.hodoledu.request.PostCreate;
import com.hodoledu.request.PostEdit;
import com.hodoledu.response.PostResponse;
import com.hodoledu.service.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class PostControllerTest {


    //json 오브젝트 검증 하는요령 까지 알아보기
    @Autowired
    MockMvc mockMvc;


    @Autowired
    PostService postService;


    @Autowired
    private PostRepository postRepository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
        //각각의 테스트메소드들이 수행될때마다 수행시켜서 초기화 하는 작업을뜻함
    void clean() {
        postRepository.deleteAll();
    }


//    @Test
//    @DisplayName("/posts 요청시 title 값은 필수.")
//    void test() throws Exception {
//        //given
//        PostCreate request = PostCreate.builder().title("제목입니다.").content("내용입니다.").build();
//        String json = objectMapper.writeValueAsString(request);
//        mockMvc.perform(post("/posts")
//                        .contentType(APPLICATION_JSON)
//                        .content(json)
//                )//    @Test
////    @DisplayName("/posts 요청시 title 값은 필수.")
////    void test2() throws Exception {
////        mockMvc.perform(post("/posts").contentType(APPLICATION_JSON)
////                        .content("{\"title\": \"제목입니다.\", \"content\": \"내용입니다.\"}")
////                )
////                .andExpect(status().isOk())
////                .andExpect((ResultMatcher) content().string("Hello World"))
////                .andDo(print());
////
////    }
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) content().string(""))
//                .andDo(print());
//    }
//
//



    //제이슨 유형의 데이터 검증 처리
    @Test
    @DisplayName("/posts 요청시 디비에 값 저장")
    void test3() throws Exception {



        PostCreate request = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/posts")
                        .header("authorization", "hodoledu")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andDo(print());

        assertEquals(1L, postRepository.count());

        Post post = postRepository.findAll().get(0);

        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }

//    @Test
//    @DisplayName("글 1개 조회")
//    void Test4() throws Exception {
//        //given
//        Post requestPost = Post.builder()
//                .title("foo")
//                .content("bar")
//                .build();
//
//        postRepository.save(requestPost);
//
//        //when
//        PostResponse postResponse = postService.get(requestPost.getId());
//
//
//        //then
//        mockMvc.perform(get("/posts/{postId}", postResponse.getId())
//                        .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(postResponse.getId()))
//                .andExpect(jsonPath("$.title").value("foo"))
//                .andExpect(jsonPath("$.content").value("bar"))
//                .andDo(print());
//    }


    @Test
    @DisplayName("글 페이지 조회")
    void Test5() throws Exception {
        //given
        List<Post> requestPosts = IntStream.range(1, 31)
                .mapToObj(i -> Post.builder()
                        .title("호돌맨 제목 " + i)
                        .content("반포자이 " + i)
                        .build())
                .collect(Collectors.toList());

        postRepository.saveAll(requestPosts);

        //then
        mockMvc.perform(get("/posts?page=1&sort=id,desc&size=5")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    @DisplayName("글 제목 수정")
    void Test7() throws Exception {
        Post post = Post.builder()
                .title("호돌교육")
                .content("반포자이")
                .build();
        postRepository.save(post);


        PostEdit postEdit = PostEdit.builder()
                .title("호돌걸")
                .content("초가집")
                .build();

        //then
        mockMvc.perform(patch("/posts/{postId}", post.getId())
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postEdit)))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    @DisplayName("게시글 삭제")
    void test8() throws Exception {
        Post post = Post.builder()
                .title("호돌맨")
                .content("반포자이")
                .build();
        postRepository.save(post);

        mockMvc.perform(delete("/posts/{postId}", post.getId())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

//    @Test
//    @DisplayName("존재하지 않는 게시글 조회")
//    void test9() throws Exception {
//        mockMvc.perform(delete("/posts/{postId}", 1L)
//                        .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }


    @Test
    @DisplayName("게시글 작성 시 제목에 '바보는 포함될 수 없다.'")
    void test10() throws Exception {
        //given
        PostCreate request = PostCreate.builder()
                .title("나는 바보입니다.")
                .content("반포자이")
                .build();

        String json = objectMapper.writeValueAsString(request);
        //when
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    //Spring RestDocs
    //운영 코드에 - > 영향 없음.
    // Test 케이스 실행 -> 문서를 새로 생성해주는 형태의 rlsmd


}
