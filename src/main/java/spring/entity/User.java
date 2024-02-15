package spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "base_id_gen",sequenceName = "user_seq",allocationSize = 1)
public class User extends BaseID{

    private String userName;
    private String password;
    private String email;
    private String phone_number;

    @OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Comment> comments;
    @OneToOne
    private Follower follower;
    @ManyToOne
    private Image image;
    @OneToMany(mappedBy = "posts")
    private List<Post> posts;
    @OneToOne
    private UserInfo userInfo;
}
