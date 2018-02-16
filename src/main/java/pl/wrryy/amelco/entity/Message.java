package pl.wrryy.amelco.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User fromUser;
    @ManyToOne
    private User toUser;

    private String text;
    private LocalDateTime created;

    public String getCreatedd() {
        return Arrays.toString(created.withSecond(0).withNano(0).toString().split("T"));
    }

}
