package com.hodoledu.controller;


import com.hodoledu.config.AppConfig;
import com.hodoledu.request.Signup;
import com.hodoledu.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AppConfig appConfig;
    private final AuthService authService;

    //json 으로 아이디/비밀번호
    //DB서 조회
    //토큰응답 순으로 구현
//    @PostMapping("jakarta")
//    public ResponseEntity<Object> login(@RequestBody Login login) {
//        String accessToken = authService.signIn(login);
//
//        //쿠키 기반 인증일때 아래옵션들 다알고있어야함. 지피티핈후
//        ResponseCookie cookie = ResponseCookie.from("SESSION", accessToken)
//                .domain("localhost") // todo 서버 환경에 따른 분리 필요
//                .path("/")
//                .httpOnly(true)
//                .secure(false)
//                .maxAge(Duration.ofDays(30))
//                .sameSite("Strict")
//                .build();
//
//        log.info(">>>>>>>>>>>>>> cookie={}", cookie.toString());
//
//        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).build();
//    }


    // jwt 종류에는 JWS, JWT, JWE 가 있다 개념알고 지나갈 것

    @GetMapping("/auth/login")
    public String login(){
        return "로그인 페이지입니다.";
    }


    @PostMapping("/auth/signup")
    public void signup(@RequestBody Signup signup){
        authService.signup(signup);
    }
}
