package com.hodoledu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hodoledu.domain.Post;
import com.hodoledu.repository.PostRepository;
import org.apache.catalina.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "api.hodoledu.com", uriPort = 443)
@ExtendWith(RestDocumentationExtension.class)

public class PostControllerDocTest {
    // 이걸 미리세팅해줘야한다 setUp
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ObjectMapper objectMapper;

//    @BeforeEach
//    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .apply(documentationConfiguration(restDocumentation))
//                .build();
//    }


//    @Test
//    @DisplayName("글 단건 조회 테스트")
//    void test1() throws Exception {
//        Post requestPost = Post.builder()
//                .title("제목")
//                .content("내용")
//                .build();
//        postRepository.save(requestPost);
//
//        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/posts/{postId}", 1240L)
//                        .accept(APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andDo(document("post-inquiry",
//                        pathParameters(
//                                parameterWithName("postId").description("게시글 ID")
//                        ),
//                        responseFields(
//                                fieldWithPath("id").description("게시글  ID"),
//                                fieldWithPath("title").description("제목"),
//                                fieldWithPath("content").description("내용")
//                        )
//                ));
//    }


    @Test
    @DisplayName("/posts 글 등록")
    void test3() throws Exception {
        //given
        Post requestPost = Post.builder()
                .title("제목")
                .content("내용")
                .build();
        String json = objectMapper.writeValueAsString(requestPost);


        mockMvc.perform(RestDocumentationRequestBuilders.post("/posts")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
                        .content(json)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("post-create",
                        requestFields(
                                fieldWithPath("id").description("게시글  ID"),
                                fieldWithPath("title").description("제목"),
                                fieldWithPath("content").description("내용").optional()
                        )
                ));
    }
}


