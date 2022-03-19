package Basic.Blog.repository;

import Basic.Blog.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public void save(Post post){
        em.persist(post);
    }

    public List<Post> findAll(){
        return em.createQuery("select p from Post p", Post.class)
                .getResultList();
    }

    public List<Post> findById(Long postId){
        return em.createQuery("select p from Post p where p.id=:postId", Post.class)
                .setParameter("postId", postId)
                .getResultList();
    }

    //수정, 삭제

}
