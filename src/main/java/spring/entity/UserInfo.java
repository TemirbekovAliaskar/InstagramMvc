package spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import spring.entity.enums.Gender;

import static jakarta.persistence.CascadeType.REFRESH;

@Entity
@Table(name = "userInfos")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "base_id_gen",sequenceName = "userInfo_seq",allocationSize = 1,initialValue = 2)
public class UserInfo extends BaseID {

    @Column(name = "full_name")
    private String fullName;
    private String biography;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String image;

    //*********************************************
    @OneToOne
    private User user;
}
