package pl.wrryy.amelco.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class SubscriptionTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @ManyToMany
    private Set<User> users;

    @Override
    public String toString() {
        return "SubscriptionTopic{" +
                "name='" + name + '\'' +
                '}';
    }
}
