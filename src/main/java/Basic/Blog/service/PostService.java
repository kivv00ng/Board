package Basic.Blog.service;

import Basic.Blog.domain.Member;
import Basic.Blog.domain.Post;
import Basic.Blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void save(Post post){
        postRepository.save(post);
    }

}
