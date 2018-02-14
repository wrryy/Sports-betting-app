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
    private byte outcome;
    @ManyToOne
    private BetCategory betCategory;
    @ManyToOne
    private Rate rate;
    private boolean active; // true if associated game not finished
    private boolean won;

    public boolean isGameFinished(){
        return this.game.isEnded();
    }
}

