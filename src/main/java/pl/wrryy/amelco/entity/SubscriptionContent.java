package pl.wrryy.amelco.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class SubscriptionContent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToMany
    private Set<User> users;

    @ManyToOne
    private SubscriptionTopic topic;
    private String content;

    @Override
    public String toString() {
        return "SubscriptionContent{" +
                "topic=" + topic +
                ", content='" + content + '\'' +
                '}';
    }
}

