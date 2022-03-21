package Basic.Blog.service;

import Basic.Blog.domain.Post;
import Basic.Blog.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired  PostRepository postRepository;
    @Autowired  PostService postService;


    @Test
    public void 글작성() throws Exception{
        //given
        Post post = Post.CreatePost("title11","content11");
        //when
        postRepository.save(post);
        Post findPost = postRepository.findOne(post.getId());
        //then
        assertThat(findPost).isEqualTo(post);
    }

    @Test
    public void 글수정() throws Exception{
        //given
        Post post1 = Post.CreatePost("title22","content22");
        Post post2 = Post.CreatePost("title22","content22");
        //when

        postRepository.save(post2);
        Post findPost = postRepository.findOne(post2.getId());
        findPost.changePost("333","333");

        //then

        assertNotEquals(post1.getTitle(), post2.getTitle());
        assertNotEquals(post1.getContent(), post2.getContent());

    }
}