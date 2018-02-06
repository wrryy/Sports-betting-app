package pl.wrryy.amelco.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany
    @NotNull
    private List<User> users;

    @ManyToMany
    @NotNull
    private List<Game> games;

    @ManyToOne
    private Rate rate;
    private BigDecimal stake;
    private LocalDateTime created;
    private boolean active;

}
