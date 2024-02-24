package spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import static jakarta.persistence.CascadeType.DETACH;
import static jakarta.persistence.CascadeType.PERSIST;

@Entity
@Table(name = "images")
@Getter
@Setter
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "base_id_gen",sequenceName = "image_seq",allocationSize = 1)
public class Image  extends BaseID {
    private String imageURL;

    //*********************************************
    @ManyToOne(cascade = PERSIST)
    private Post post;
    @OneToMany(cascade = {DETACH})
    private List<User> users;

}