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


}