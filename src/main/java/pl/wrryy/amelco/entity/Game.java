package pl.wrryy.amelco.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    private List<Team> teams;

    @ManyToOne
    private Sport sport;

    private int[] result;
    private LocalDateTime started;
    private LocalDateTime ended;
    private boolean active;

}
