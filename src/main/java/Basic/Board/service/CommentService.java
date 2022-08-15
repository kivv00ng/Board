package Basic.Board.service;

import Basic.Board.domain.Comment;
import Basic.Board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    public void save(Comment comment){
        commentRepository.save(comment);
    }

    public Comment findOne(Long id){
        return commentRepository.findOne(id);
    }

    public List<Comment> findAll(){
        return commentRepository.findAll();
    }

    public void changeComment(Long commentId, String content){
        Comment findComment = commentRepository.findOne(commentId);
        findComment.changeComment(content);
    }

    public void deleteComment(Long commentId){
        commentRepository.delete(commentId);
    }
}
