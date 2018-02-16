package pl.wrryy.amelco.service;

import org.springframework.stereotype.Service;
import pl.wrryy.amelco.entity.Message;
import pl.wrryy.amelco.entity.User;
import pl.wrryy.amelco.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void saveMessage(Message message) {
        message.setCreated(LocalDateTime.now());
        messageRepository.save(message);
    }

    public List<Message> getConversationWithUser(User loggedUser, User toUser) {
        List<Message> messages = messageRepository.findAllByFromUserAndToUserOrderByCreatedDesc(loggedUser, toUser);
        messages.addAll(messageRepository.findAllByFromUserAndToUserOrderByCreatedDesc(toUser, loggedUser));
        messages = messages.stream().sorted(Comparator.comparing(Message::getCreated).reversed()).collect(Collectors.toList());
        return messages;
    }

    public List<Message> getMessagesByUser(User loggedUser) {
        List<Message> messages = messageRepository.findAllByFromUserOrderByCreatedDesc(loggedUser);
        List<Message> messages1 = messageRepository.findAllByToUserOrderByCreatedDesc(loggedUser);
        if (messages != null) {
            messages.addAll(messages1);
            messages = messages.stream().sorted(Comparator.comparing(Message::getCreated).reversed()).collect(Collectors.toList());
        }
        return messages;
    }

}
