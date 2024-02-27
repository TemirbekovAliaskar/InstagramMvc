package spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "posts")
@Getter
@Setter
@SequenceGenerator(name = "base_id_gen",sequenceName = "post_seq",allocationSize = 1)
public class Post extends BaseID {

    private String title;
    private String description;
    @Column(name = "created_At")
    private Date createdAt = Date.from(Instant.now()) ;

    @OneToMany(cascade = {REMOVE,PERSIST}, mappedBy = "post",fetch = FetchType.EAGER)
    private List<Image> images;

    //*********************************************
    @OneToMany(cascade = {ALL}, mappedBy = "post", fetch = FetchType.EAGER)
    private List<Comment> comments;
    //*********************************************
    @OneToMany(cascade = {REMOVE,PERSIST}, mappedBy = "post", fetch = FetchType.EAGER)
    private List<Like> likes ;
    //*********************************************

    //*********************************************
    @ManyToOne(cascade = {DETACH,PERSIST},fetch = FetchType.EAGER)
    private User user;
    @Transient
    private String imageURL;

    public void addImage(Image image) {
        if (images == null) images = new ArrayList<>();
        images.add(image);
    }


    public void addComment(Comment comment){
        if (this.comments == null) this.comments = new ArrayList<>();
        this.comments.add(comment);
    }

    public void addLike(Like like) {
        if (this.likes == null) this.likes = new ArrayList<>();
        this.likes.add(like);
    }
}
