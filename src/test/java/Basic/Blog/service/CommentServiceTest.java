package Basic.Blog.service;

import Basic.Blog.domain.Comment;
import Basic.Blog.repository.CommentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentServiceTest {

    @Autowired CommentRepository commentRepository;
    @Autowired CommentService commentService;

    @Test
    public void 댓글저장() throws Exception{
        //given
        Comment comment = Comment.CreateComment("댓글1");
        //when
        commentService.save(comment);
        Comment findComment = commentService.findOne(comment.getId());
        //then
        assertThat(findComment).isEqualTo(comment);
    }
    @Test
    public void 댓글수정() throws Exception{
        //given
        Comment comment1 = Comment.CreateComment("댓글111");
        Comment comment2 = Comment.CreateComment("댓글111");
        //when
        commentService.save(comment2);
        commentService.changeComment(comment2.getId(),"댓글222");

        //then
        assertNotEquals(comment1.getContent(), comment2.getContent());
    }

}