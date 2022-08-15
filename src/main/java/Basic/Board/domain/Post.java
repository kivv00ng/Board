package Basic.Board.domain;

import lombok.Getter;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    List<Comment> comments = new ArrayList<>();

    private String title;

    public void setMember(Member member) {
        this.member = member;
    }

    @Column(name = "post_content")
    private String content;

    private LocalDateTime createDate;


    protected Post() {}
    public static Post CreatePost(String title, String content){
        Post post = new Post();

        post.title= title;
        post.content = content;
        post.createDate = LocalDateTime.now();

        return post;
    }

    public void changePost(String title, String content){
        this.title= title;
        this.content = content;
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
        comment.setPost(this);
    }


}
