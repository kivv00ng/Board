package Basic.Blog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String Email;

    private String name;
    private String password;

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    protected Member() {}
    public static Member CreateMember(String Email, String name, String password){
        Member member = new Member();

        member.Email = Email;
        member.name = name;
        member.password = password;

        return member;
    }

    public void addPost(Post post){
        this.posts.add(post);
        post.setMember(this);
    }


    public void addComment(Post post,Comment comment){
        this.comments.add(comment);
        post.getComments().add(comment);
        comment.setMember(this);
    }


}
