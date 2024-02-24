package spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import static jakarta.persistence.CascadeType.DETACH;

@Entity
@Table(name = "followers")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "base_id_gen",sequenceName = "follower_seq",allocationSize = 1,initialValue = 2)
public class Follower  extends BaseID {

    @ElementCollection
    private List<Long> subscribers;
    @ElementCollection
    private List<Long> subscriptions;
    //*********************************************
    @OneToOne
    private User user;



}
