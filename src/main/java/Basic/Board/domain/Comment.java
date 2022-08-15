package Basic.Board.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="post_id")
    private Post post;

    @Column(name="comment_content")
    private String content;

    public void setMember(Member member) {
        this.member = member;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    protected Comment(){};

    public static Comment CreateComment(String content){
        Comment comment = new Comment();
        comment.content = content;

        return comment;
    }
    public void changeComment(String content){
        this.content = content;
    }

}
