package Basic.Board.service;

import Basic.Board.domain.Member;
import Basic.Board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String Email, String password) {
        Optional<Member> findMember = memberRepository.findByEmail(Email);

        if(findMember.isEmpty()){
            return null;
        }

        Member member = findMember.get();
        if(member.getPassword().equals(password)){
            return member;
        }
        return null;
    }

}
