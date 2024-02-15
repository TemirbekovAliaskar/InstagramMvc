package spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "base_id_gen",sequenceName = "comment_seq",allocationSize = 1)
public class Comment extends BaseID {

    private String comment;
    private LocalDate createdAt;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Post post;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;
    @OneToMany(cascade = {CascadeType.REMOVE})
    private List<Like> likes;


}
