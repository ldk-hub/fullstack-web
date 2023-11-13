package com.hodoledu.repository;

import com.hodoledu.domain.Post;
import com.hodoledu.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
