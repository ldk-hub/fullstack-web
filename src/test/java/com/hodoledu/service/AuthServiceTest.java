package com.hodoledu.service;

import com.hodoledu.domain.User;
import com.hodoledu.repository.UserRepository;
import com.hodoledu.request.Signup;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles
@SpringBootTest
class AuthServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @AfterEach
    void clean() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입 시 중복 된 이메일")
    void test1() {
//        User user = User.builder()
//                .pw("1234")
//                .email("hodolman@gmail.com")
//                .name("짱동맨")
//                .build();
//
//        userRepository.save(user);

        //given
        Signup signup = Signup.builder()
                .password("1234")
                .email("hodolman@gmail.com")
                .name("호돌맨")
                .build();

        //when
        authService.signup(signup);
        //expected
//        assertThrows(AlreadyExistsEmailException.class, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                authService.signup(signup);
//            }
//        });
        //then
        Assertions.assertEquals(1, userRepository.count());
        User user = userRepository.findAll().iterator().next();
        assertEquals("hodolman@gmail.com", user.getEmail());
        assertNotNull(user.getPw());
        assertEquals("1234", user.getPw());
        assertEquals("호돌맨", user.getName());


    }

//    @Test
//    @DisplayName("로그인 성공")
//    void test2() {
//        //given
//        ScryptPasswordEncoder encoder = new ScryptPasswordEncoder();
//        String encryptPassword = encoder.encrypt("1234");
//
//        User user = User.builder()
//                .pw(encryptPassword)
//                .email("hodolman@gmail.com")
//                .name("짱동맨")
//                .build();
//
//        userRepository.save(user);
//        //when
//
//        Login login = Login.builder()
//                .pw("1234")
//                .email("hodolman@gmail.com")
//                .build();
//        Long userId = authService.signIn(login);
//
//        assertNotNull(login);
//
//    }
//
//
//    @Test
//    @DisplayName("로그인 시 비밀번호 틀림")
//    void test3() {
//        //given
//        Signup signup = Signup.builder()
//                .password("1234")
//                .email("hodolman@gmail.com")
//                .name("호돌맨")
//                .build();
//        authService.signup(signup);
//        //when
//
//        Login login = Login.builder()
//                .pw("5678")
//                .email("hodolman@gmail.com")
//                .build();
//
//        Assertions.assertThrows(InvalidSigninInformation.class, () -> authService.signIn(login));
//
//
//    }


}
