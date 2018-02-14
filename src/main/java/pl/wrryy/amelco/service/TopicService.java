package pl.wrryy.amelco.service;

import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.SubscriptionTopic;
import pl.wrryy.amelco.entity.User;
import pl.wrryy.amelco.repository.SubTopicRepository;

import java.util.List;
import java.util.Set;

@Service
public class TopicService {
    private SubTopicRepository repository;
    public TopicService(SubTopicRepository repository) {
        this.repository = repository;
    }

    public List<SubscriptionTopic> findAll(){ return repository.findAll();}
    public SubscriptionTopic findTopicByName(String name){ return repository.findByName(name);}
    public void saveTopic(SubscriptionTopic content){ repository.save(content);}
    public void deleteTopic(long id){ repository.delete(id);}

    public void addUserToTopic(SubscriptionTopic topic, User user){
        Set<User> users = topic.getUsers();
        users.add(user);
        topic.setUsers(users);
        saveTopic(topic);
    }
}
