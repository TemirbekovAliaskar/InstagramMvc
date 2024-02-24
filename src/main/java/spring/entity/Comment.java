package spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.CascadeType.DETACH;
import static jakarta.persistence.CascadeType.REMOVE;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "base_id_gen",sequenceName = "comment_seq",allocationSize = 1)
public class Comment extends BaseID {

    private String comment;
    @Column(name = "created-at")
    private LocalDate createdAt = LocalDate.now();

    @ManyToOne(cascade = {DETACH})
    private Post post;
    @ManyToOne(cascade = {DETACH})
    private User user;
    @OneToMany(cascade = {REMOVE}, mappedBy = "comment")
    private List<Like> likes;





}
