package pl.wrryy.amelco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wrryy.amelco.entity.Message;
import pl.wrryy.amelco.entity.User;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long>{
    public List<Message> findAllByFromUserAndToUserOrderByCreatedDesc(User fromUser, User toUser);
    public List<Message> findAllByFromUserOrderByCreatedDesc(User user);
    public List<Message> findAllByToUserOrderByCreatedDesc(User user);
}
