package pl.wrryy.amelco.service;

import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.SubscriptionContent;
import pl.wrryy.amelco.entity.User;
import pl.wrryy.amelco.repository.SubContentRepository;

import java.util.List;

@Service
public class ContentService {
    private SubContentRepository repository;

    public ContentService(SubContentRepository repository) {
        this.repository = repository;
    }
    public List<SubscriptionContent> findAll(){ return repository.findAll();}
    public List<SubscriptionContent> findAllByUser(User user){ return repository.findAllByUsers(user);}
    public void saveContent(SubscriptionContent content){ repository.save(content);}
    public void deleteContent(long id){ repository.delete(id);}
}
