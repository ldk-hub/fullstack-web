package com.hodoledu.repository;

import com.hodoledu.domain.Post;
import com.hodoledu.domain.QPost;
import com.hodoledu.request.PostSearch;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PostRepositoryImpl implements  PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Post> getList(PostSearch postSearch) {
        return jpaQueryFactory.selectFrom(QPost.post)
                .limit(postSearch.getSize())
                .offset((long)(postSearch.getPage() -1) * postSearch.getSize())
                .orderBy(QPost.post.id.desc())
                .fetch();
    }
}
