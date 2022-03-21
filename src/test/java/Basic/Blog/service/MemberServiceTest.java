package Basic.Blog.service;

import Basic.Blog.domain.Member;
import Basic.Blog.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberServiceTest {


    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = Member.CreateMember("rldnd2637@","kimkiwoong","123");

        //when
        memberRepository.save(member);
        Member findMember = memberService.findOne(member.getId());
        //then
        Assertions.assertThat(findMember).isEqualTo(member);

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


}