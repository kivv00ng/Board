package Basic.Board.service;

import Basic.Board.domain.Post;
import Basic.Board.repository.PostRepository;
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
    @Autowired CommentService commentService;


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
/*
    @Test
    public void 게시글에댓글추가() throws Exception{
        //given
        Post post = Post.CreatePost("111","111");
        Comment comment = Comment.CreateComment("댓글");
        //when
        postRepository.save(post);
        commentService.save(comment);

        post.addComment(comment);
        //then
        assertThat(comment.getPost()).isEqualTo(post);
    }

 */
}