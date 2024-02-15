package spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "base_id_gen",sequenceName = "post_seq",allocationSize = 1)
public class Post extends BaseID {

    private String title;
    private String description;
    private LocalDate createdAt;
    @OneToMany(mappedBy = "post",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Comment> comments;

    @OneToMany(mappedBy = "post",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Image> images;

    @OneToMany(mappedBy = "post",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Like> likes;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private User user;
}
