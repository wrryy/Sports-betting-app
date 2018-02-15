package pl.wrryy.amelco.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

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
    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    private BetCategory betCategory;

    @ManyToOne
    private Rate rate;
    private boolean active; // true if associated game not finished
    private boolean won;

    public boolean isGameFinished(){
        return this.game.isEnded();
    }

    @Override
    public String toString() {
        return game.thatTeams() +" "+ game.getStartedd()+
                ", " + betCategory.getName() +
                ", stake=" + stake +
                ", outcome=" + outcome
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bet bet = (Bet) o;
        return outcome == bet.outcome &&
                active == bet.active &&
                won == bet.won &&
                Objects.equals(id, bet.id) &&
                Objects.equals(game, bet.game) &&
                Objects.equals(coupon, bet.coupon) &&
                Objects.equals(stake, bet.stake) &&
                Objects.equals(betCategory, bet.betCategory) &&
                Objects.equals(rate, bet.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, stake, outcome);
    }
}

