package pl.wrryy.amelco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wrryy.amelco.entity.SubscriptionTopic;
@Repository
public interface SubTopicRepository extends JpaRepository<SubscriptionTopic, Long>{
    public  SubscriptionTopic findByName(String name);
}
