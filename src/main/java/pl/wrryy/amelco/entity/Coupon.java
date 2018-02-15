package pl.wrryy.amelco.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @NotNull
    private User user;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL)
    private List<Bet> bets = new ArrayList<>();

    private LocalDateTime created;
    private boolean active;

    public String getCreatedd() {
        return Arrays.toString(created.withSecond(0).withNano(0).toString().split("T")); }

    @Override
    public String toString() {
        return bets.toString();
    }
}
