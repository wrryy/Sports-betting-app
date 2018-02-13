package pl.wrryy.amelco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wrryy.amelco.entity.SubscriptionContent;
import pl.wrryy.amelco.entity.SubscriptionTopic;
import pl.wrryy.amelco.entity.User;

import java.util.List;

@Repository
public interface SubContentRepository extends JpaRepository<SubscriptionContent, Long>{
    List<SubscriptionContent> findAllByUsers(User user);

}
