package spring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import spring.entity.enums.Gender;

@Entity
@Table(name = "userInfos")
@Getter
@Setter
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "base_id_gen",sequenceName = "userInfo_seq",allocationSize = 1)
public class UserInfo extends BaseID {

    private String fullName;
    private String biography;
    private Gender gender;
    private String imageLink;

    @OneToOne(mappedBy = "userInfo")
    private User user;
}
