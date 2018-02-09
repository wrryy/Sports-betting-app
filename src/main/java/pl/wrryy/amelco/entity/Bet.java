package pl.wrryy.amelco.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @NotNull
    private Game game;
    @ManyToOne
    @NotNull
    private Coupon coupon;
    private BigDecimal stake;

    @ManyToOne
    private Rate rate;
//    private boolean active;
}
