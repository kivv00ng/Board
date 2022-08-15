package Basic.Board.repository;

import Basic.Board.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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

    public void delete(Long commentId){
        em.createQuery("delete from Comment c where c.id=:commentId")
                .setParameter("commentId", commentId)
                .executeUpdate();

    }




}
