package Basic.Blog.service;

import Basic.Blog.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LoginServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired MemberService memberService;

    @Test
    public void LoginServiceTest() throws Exception{
        //given


        //when

        //then
    }



}