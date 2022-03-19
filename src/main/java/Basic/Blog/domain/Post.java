package Basic.Blog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "post")
    List<Comment> comments = new ArrayList<>();

    private String title;

    @Column(name = "post_content")
    private String content;

    private LocalDateTime createDate;

    protected Post() {}
    public static Post CreatePost(String title, String content, LocalDateTime createDate){
        Post post = new Post();

        post.title= title;
        post.content = content;
        post.createDate = LocalDateTime.now();

        return post;
    }


}
