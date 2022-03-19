package Basic.Blog.service;

import Basic.Blog.domain.Member;
import Basic.Blog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;

    //회원 가입
    @Transactional
    public void save(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> findMembers = memberRepository.findByEmail(member.getEmail());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원 입니다.");
        }
    }
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

    public Optional<Member> findByEmail(String Email){
        return memberRepository.findByEmail(Email);
    }

    public Optional<Member> findById(Long id){return memberRepository.findById(id);}
}
