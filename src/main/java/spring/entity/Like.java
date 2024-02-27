package spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import static jakarta.persistence.CascadeType.DETACH;

@Entity
@Table(name = "likes")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "base_id_gen",sequenceName = "like_seq",allocationSize = 1)
public class Like extends BaseID {

    private Boolean isLike ;

    //*********************************************
    //ManyToOne
    @OneToOne
    private User user;
    //*********************************************
    @ManyToOne()
    private Comment comment;
    //*********************************************
    @ManyToOne(cascade = {DETACH})
    private Post post;
}
