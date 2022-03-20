package Basic.Blog.service;

import Basic.Blog.controller.post.PostForm;
import Basic.Blog.domain.Member;
import Basic.Blog.domain.Post;
import Basic.Blog.repository.PostRepository;
import com.zaxxer.hikari.metrics.PoolStats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void save(Post post){
        postRepository.save(post);
    }

    public Post findOne(Long id){
        return postRepository.findOne(id);
    }
    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public void updatePost(Long postId, String title, String content) {
        Post findPost = postRepository.findOne(postId);

        findPost.changePost(title, content);
    }

}
