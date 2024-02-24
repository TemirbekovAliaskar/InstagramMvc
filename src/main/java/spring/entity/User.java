package spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "base_id_gen", sequenceName = "user_seq", allocationSize = 1,initialValue = 2)
public class User extends BaseID {

    @Column(unique = true)
    private String userName;
    private String password;
    @Column(unique = true)
    private String email;
    private String phoneNumber;

//*********************************************
    @OneToOne(mappedBy = "user" ,cascade = {REMOVE, PERSIST,})
    private UserInfo userInfo;
    @OneToOne(mappedBy = "user",cascade = {REMOVE, PERSIST})
    private Follower follower;
    @OneToMany(cascade = {REMOVE,PERSIST}, mappedBy = "user",fetch = FetchType.EAGER)
    private List<Post> posts;
    //*********************************************
    @OneToMany(cascade = {REMOVE}, mappedBy = "user")
    private List<Comment> comments;

    public void addComment(Comment comment){
        if (this.comments == null) this.comments = new ArrayList<>();
        this.comments.add(comment);
    }
}
