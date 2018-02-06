package pl.wrryy.amelco.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 50)
    private String userName;
    @Column(nullable = false, unique = true)
    @Email
    private String email;
    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;
    @NotEmpty
    private String password;

//    @OneToMany(mappedBy = "user")
//    private UserCouponBet userCouponBet;

    @ElementCollection
    private List<String> walletHistory;
    private BigDecimal walletBalance;

    @ManyToMany
    private List<User> friends;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;
    private boolean active;

}
