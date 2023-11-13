package com.hodoledu.controller;

import com.hodoledu.request.PostEdit;
import com.hodoledu.request.PostSearch;
import com.hodoledu.response.PostResponse;
import com.hodoledu.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    /*
    @PostMapping("/posts")
    public String post(@RequestBody PostCreate params) throws Exception {
        log.info("params = {}", params.toString());

        String title = params.getTitle();
        if (title == null || title.equals("")) {
            // 1.이런 유효성 체크는 빡셈
            // 2. 개발팁 -> 무언가 3번이상 반복작업시 내가 뭔가 잘못하고 있는지 의심한다.
            // 3. 누락가능성
            // 4. 생각보다 검증할게 많다.(꼼꼼하지 않은ㄹ 수 있다.)
            // 5. 뭔가 개발자스럽지 않다.
            throw new Exception("타이틀 값이 없음.");
        }
        String content = params.getContent();

        if (content == null || content.equals("")) {
            throw new Exception("에러");
        }
        return "Hello World";
    }
    */

    private final PostService postService;


//    @GetMapping("/foo")
//    public Long foo(UserSession userSession){
//        log.info(">>>{}", userSession.id);
//        return userSession.id;
//    }
//
//
//    // 응답 케이스
//    // Case1. 저장한 데이터 Entity -> response로 응답하기
//    // Case2. 저장한 데이터의 primary_id -> response로 응답하기
//    // Client에서는 수신한 id를 글 조회 API를 통해서 데이터를 수신받음
//    // Case3. 응답필요없음 -> 클라이언트에서 모든 POST(글) 데이터 context 를 잘 관리함
//    // Bad Case: 서버에서 -> 반드시 이렇게 할겁니다! fix
//    // -> 서버에서 차라리 유연하게 대응하는게 좋습니다. -> 코드를 잘짜야함.
//    @PostMapping("/posts")
//    public void post(@RequestBody @Valid PostCreate request) {
//        request.validated();
//        postService.write(request);
//    }


    /*
    posts -> 글 전체 조회(검색 + 페이징)
    /posts/{postId} 글 한개만 조회
     */

    //조회 API   = 단건 조회 API (1개의 글을 가져오는 기능)
    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId) {
        return postService.get(postId);
    }


    //조회 API   =
    // 여러개의 글을 조회 API
    @GetMapping("/posts")
    public List<PostResponse> getList(@ModelAttribute PostSearch postSearch) {
        return postService.getList(postSearch);
    }


    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit postEdit) {
        postService.edit(postId, postEdit);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId){
        postService.delete(postId);
    }

}
