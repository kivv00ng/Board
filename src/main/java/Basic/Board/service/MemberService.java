package Basic.Board.service;

import Basic.Board.domain.Member;
import Basic.Board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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



}
