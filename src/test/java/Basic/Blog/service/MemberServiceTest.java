package Basic.Blog.service;

import Basic.Blog.domain.Comment;
import Basic.Blog.domain.Member;
import Basic.Blog.domain.Post;
import Basic.Blog.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberServiceTest {


    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;
    @Autowired PostService postService;
    @Autowired CommentService commentService;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = Member.CreateMember("rldnd2637@","kimkiwoong","123");

        //when
        memberRepository.save(member);
        Member findMember = memberService.findOne(member.getId());
        //then
        assertThat(findMember).isEqualTo(member);

    }
    @Test
    public void 중복회원처리() throws Exception{
        //given
        Member member1 = Member.CreateMember("rldnd2637@","kimkiwoong","123");
        Member member2 = Member.CreateMember("rldnd2637@","kim","111");
        //when
        memberService.save(member1);
        //then
        assertThrows(IllegalStateException.class,()-> {memberService.save(member2);} );
    }
    @Test
    public void 회원에게시글추가() throws Exception{
        //given
        Member member = Member.CreateMember("rldnd2637","kiwoong","123");
        Post post = Post.CreatePost("post11","content11");
        //when
        memberRepository.save(member);
        postService.save(post);

        //로그인 이용자만 게시글작성가능, 따라서 session에서 저장된 정보로 불러와야하므로 해당 Member를 영속성상태로 만들려면 find()필요
        Member findMember = memberService.findOne(member.getId());
        findMember.addPost(post);
        //then
        assertThat(post.getMember()).isEqualTo(member);
    }
    @Test
    public void 회원과게시글에댓글추가() throws Exception{
        //given
        Member member = Member.CreateMember("rldnd2637","kiwoong","123");
        Post post = Post.CreatePost("post","content");
        Comment comment = Comment.CreateComment("댓글");

        //when
        memberRepository.save(member);
        postService.save(post);
        commentService.save(comment);

        member.addComment(post, comment);
        Member findMember = memberService.findOne(member.getId());

        //then
        assertThat(comment.getMember()).isEqualTo(member);
        assertThat(comment.getPost()).isEqualTo(post);


    }

}