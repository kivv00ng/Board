package Basic.Blog.repository;

import Basic.Blog.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public void save(Comment comment){
        em.persist(comment);
    }

    public Comment findOne(Long id){
        return em.find(Comment.class, id);
    }

    public List<Comment> findAll(){
         return em.createQuery("select c from Comment c", Comment.class)
                .getResultList();
    }



}
