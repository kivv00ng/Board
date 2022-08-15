package Basic.Board.service;

import Basic.Board.domain.Post;
import Basic.Board.repository.PostRepository;
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
