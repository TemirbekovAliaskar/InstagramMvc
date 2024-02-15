package spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "images")
@Getter
@Setter
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "base_id_gen",sequenceName = "image_seq",allocationSize = 1)
public class Image  extends BaseID{
    @Column(length = 1000)
    private String imageUrl;
    @ManyToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    private Post post;
    @OneToMany(mappedBy ="image",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<User> users;
}
