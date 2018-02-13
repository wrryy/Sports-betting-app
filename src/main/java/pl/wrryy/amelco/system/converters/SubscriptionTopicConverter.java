package pl.wrryy.amelco.system.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.wrryy.amelco.entity.SubscriptionTopic;
import pl.wrryy.amelco.repository.SubTopicRepository;

public class SubscriptionTopicConverter implements Converter<String, SubscriptionTopic> {

    @Autowired
    private SubTopicRepository topicRepository;

    @Override
    public SubscriptionTopic convert(String source) {
        SubscriptionTopic subscriptionTopic = topicRepository.findOne(Long.parseLong(source));
        return subscriptionTopic;
    }
}
