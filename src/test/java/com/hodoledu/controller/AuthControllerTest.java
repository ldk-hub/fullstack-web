package com.hodoledu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hodoledu.domain.User;
import com.hodoledu.repository.UserRepository;
import com.hodoledu.request.Signup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
        //각각의 테스트메소드들이 수행될때마다 수행시켜서 초기화 하는 작업을뜻함
    void clean() {
        userRepository.deleteAll();
    }


//    @Test
//    @DisplayName("/로그인 성공")
//    void test() throws Exception {
//
//        userRepository.save(User.builder()
//                .name("호돌맨")
//                .email("hodolman88@gmail.com")
//                .pw("1234")
//                .build());
//
//
//        Login login = Login.builder()
//                .email("hodolman88@gmail.com")
//                .pw("1234")
//                .build();
//
//        String json = objectMapper.writeValueAsString(login);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
//                        .contentType(APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isOk())
//                .andDo(print());
//
//    }
//
//
//    @Test
//    @Transactional
//    @DisplayName("/로그인 성공 후 세션 1개 생성")
//    void test2() throws Exception {
//
//        User user = userRepository.save(User.builder()
//                .name("호돌맨")
//                .email("hodolman88@gmail.com")
//                .pw("1234")
//                .build());
//
//
//        Login login = Login.builder()
//                .email("hodolman88@gmail.com")
//                .pw("1234")
//                .build();
//
//        String json = objectMapper.writeValueAsString(login);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
//                        .contentType(APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isOk())
//                .andDo(print());
//
//        Assertions.assertEquals(1L, user.getSessions().size());
//
//    }

    @Test
    @DisplayName("회원가입")
    void test6() throws Exception {
        //given
        Signup signup = Signup.builder()
                .password("1234")
                .email("hodolman@gmail.com")
                .name("호돌맨")
                .build();
        //expected
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/signup")
                        .content(objectMapper.writeValueAsString(signup))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
//    @Test
//    @DisplayName("/로그인 성공 후 세션 응답")
//    void test3() throws Exception {
//
//        User user = userRepository.save(User.builder()
//                .name("호돌맨")
//                .email("hodolman88@gmail.com")
//                .pw("1234")
//                .build());
//
//
//        Login login = Login.builder()
//                .email("hodolman88@gmail.com")
//                .pw("1234")
//                .build();
//
//        String json = objectMapper.writeValueAsString(login);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
//                        .contentType(APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.accessToken", Matchers.notNullValue()))
//                .andDo(print());
//    }
//
//
//    @Test
//    @DisplayName("/로그인 후 권한이 필요한 페이지 접속한다 /foo")
//    void test4() throws Exception {
//
//        User user = User.builder()
//                .name("호돌맨")
//                .email("hodolman88@gmail.com")
//                .pw("1234")
//                .build();
//
//        Session session = user.addSession();
//        userRepository.save(user);
//
////        String json = objectMapper.writeValueAsString(login);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/foo")
//                        .header("Authorization", session.getAccessToken())
//                        .contentType(APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//
//
//    @Test
//    @DisplayName("/로그인 후 검증되지 않은 세션값으로 권한이 필요한 페이지에 접속 할 수 없다.")
//    void test5() throws Exception {
//
//        User user = User.builder()
//                .name("호돌맨")
//                .email("hodolman88@gmail.com")
//                .pw("1234")
//                .build();
//
//        Session session = user.addSession();
//        userRepository.save(user);
//
////        String json = objectMapper.writeValueAsString(login);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/foo")
//                        .header("Authorization", session.getAccessToken()+"-o")
//                        .contentType(APPLICATION_JSON))
//                .andExpect(status().isUnauthorized())
//                .andDo(print());
//    }
}
