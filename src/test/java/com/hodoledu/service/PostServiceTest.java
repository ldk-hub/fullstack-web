package com.hodoledu.service;

import com.hodoledu.domain.Post;
import com.hodoledu.exception.PostNotFound;
import com.hodoledu.repository.PostRepository;
import com.hodoledu.request.PostCreate;
import com.hodoledu.request.PostEdit;
import com.hodoledu.request.PostSearch;
import com.hodoledu.response.PostResponse;
import com.hodoledu.service.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;


    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    void Test1() {
        //given
        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();


        //when
        postService.write(postCreate);


        //then
        assertEquals(1L, postRepository.count());
        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }


    @Test
    @DisplayName("글 1개 조회")
    void Test2() {
        //given
        Post requestPost = Post.builder()
                .title("foo")
                .content("bar")
                .build();

        postRepository.save(requestPost);

        //when
        PostResponse post = postService.get(requestPost.getId());


        //then
        assertNotNull(post);
        assertEquals("foo", post.getTitle());
        assertEquals("bar", post.getContent());
    }


    @Test
    @DisplayName("글 첫 1페이지 조회")
    void Test3() {
        //given
        List<Post> requestPosts = IntStream.range(1, 31)
                .mapToObj(i -> Post.builder()
                        .title("호돌맨 제목 " + i)
                        .content("반포자이 " + i)
                        .build())
                .collect(Collectors.toList());

        postRepository.saveAll(requestPosts);

        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id");

        PostSearch postSearch = PostSearch.builder()
                .page(1)
                .build();
        //when
        List<PostResponse> postList = postService.getList(postSearch);


        //then
        assertEquals(10L, postList.size());
        assertEquals("호돌맨 제목 30", postList.get(0).getTitle());
    }


    @Test
    @DisplayName("글 제목 수정")
    void test4() {
        //given
        Post post = Post.builder()
                .title("호돌교육")
                .content("반포자이")
                .build();

        postRepository.save(post);


        PostEdit postEdit = PostEdit.builder()
                .title("호돌걸")
                        .build();
        //when

        postService.edit(post.getId(), postEdit);
        //then

        Post changedPost = postRepository.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다 id = "+ post.getId()));
        Assertions.assertEquals("호돌걸", changedPost.getTitle());
    }



    @Test
    @DisplayName("글 제목 수정")
    void test5() {
        //given
        Post post = Post.builder()
                .title("호돌교육")
                .content("반포자이")
                .build();

        postRepository.save(post);


        PostEdit postEdit = PostEdit.builder()
                .title("호돌걸")
                .build();
        //when

        postService.edit(post.getId(), postEdit);
        //then

        Post changedPost = postRepository.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다 id = "+ post.getId()));
        Assertions.assertEquals("호돌걸", changedPost.getTitle());
    }



    @Test
    @DisplayName("글 내용 수정")
    void test6() {
        //given
        Post post = Post.builder()
                .title("호돌교육")
                .content("반포자이")
                .build();

        postRepository.save(post);


        PostEdit postEdit = PostEdit.builder()
                .title("호돌걸")
                .content("초가집")
                .build();
        //when

        postService.edit(post.getId(), postEdit);
        //then

        Post changedPost = postRepository.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다 id = "+ post.getId()));
        Assertions.assertEquals("초가집", changedPost.getContent());
    }

    @Test
    @DisplayName("게시글 삭제")
    void test7() {
        //given
        Post post = Post.builder()
                .title("호돌교육")
                .content("반포자이")
                .build();

        postRepository.save(post);

        postService.delete(post.getId());


        assertEquals(0, postRepository.count());

    }


    @Test
    @DisplayName("글 1개  조회")
    void test8() {
        //given
        Post post = Post.builder()
                .title("호돌맨")
                .content("반포자이")
                .build();

        postRepository.save(post);

        assertThrows(PostNotFound.class, () -> postService.get(post.getId() + 1L));

    }


}
