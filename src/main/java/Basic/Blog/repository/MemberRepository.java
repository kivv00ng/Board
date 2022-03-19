package Basic.Blog.repository;

import Basic.Blog.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {


    //@PersistenceContext //스프링이 EntityManager를 만들어서 해당 어노테이션에 해당하는 레퍼에 주입
    // spring boot 사용시 @Autowired로도 EntityMangaer에 주입가능하도록함=> @RequiredArgsConstructor로도 주입 가능
    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }
/*
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
*/
    public Optional<Member> findById(Long id){
        List<Member> findMember = em.createQuery("select m from Member m where m.id =: id", Member.class)
                .setParameter("id", id)
                .getResultList();
        for (Member m : findMember) {
            if(m.getId().equals(id)) {
                return Optional.of(m);
            }
        }
        return Optional.empty();
    }

    public Optional<Member> findByEmail(String email){
        List<Member> findMember = em.createQuery("select m from Member m where m.Email =: email", Member.class)
                .setParameter("email", email)
                .getResultList();
        for (Member m : findMember) {
            if(m.getEmail().equals(email)) {
                return Optional.of(m);
            }
        }
        return Optional.empty();
    }


}
